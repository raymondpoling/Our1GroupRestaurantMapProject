package org.americanairlines.our1grouprestaurantmapproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.android.gms.maps.model.LatLng
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.data.PlaceDatabase
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger
import org.americanairlines.our1grouprestaurantmapproject.view.ListNearbyPlacesActivity

class PlaceResultRepository {

    private val placeDatabase: PlaceDatabase = Room.databaseBuilder(
        ListNearbyPlacesActivity.getContext(),
        PlaceDatabase::class.java,
        PlaceDatabase.DATABASE_NAME
    ).build()

    fun getNearbyPlaces(location: LatLng): LiveData<List<NearbyPlacesModel>> {
        DebugLogger.logger("Trying to decode location: $location")
        refresh(location)
        return placeDatabase.getPlacesDAO().getFromLocation(location.latitude, location.longitude)
    }

    private fun refresh(location: LatLng) {
        Thread {
            val response = PlaceRetrofit.getNearbyPlaces(location).execute()
            if (response.isSuccessful) {
                DebugLogger.logger("We got body: ${response.body()}")
                response.body()?.also {
                    placeDatabase.getPlacesDAO().insertPlaces(onSuccess(it))
                }
            }
        }.start()
    }


    private fun onSuccess(placeResponse: PlaceResponse) : List<NearbyPlacesModel> =
                placeResponse.results.map { t ->
                    NearbyPlacesModel(
                        t.geometry.location.lat,
                        t.geometry.location.lng,
                        t.vicinity,
                        t.types.first(),
                        t.name)
                }

    private fun onFailure(t: Throwable) : Unit {
        DebugLogger.elogger("Received an error: ${t.message.toString()}")
    }


}