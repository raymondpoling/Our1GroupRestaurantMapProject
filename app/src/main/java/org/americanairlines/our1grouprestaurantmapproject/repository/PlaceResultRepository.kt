package org.americanairlines.our1grouprestaurantmapproject.repository

import androidx.lifecycle.LiveData
import androidx.room.Room
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.data.PlaceDatabase
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger
import org.americanairlines.our1grouprestaurantmapproject.view.ListNearbyPlacesActivity

typealias PlaceSet = List<NearbyPlacesModel>

class PlaceResultRepository {

    private val placeDatabase: PlaceDatabase = Room.databaseBuilder(
        ListNearbyPlacesActivity.getContext(),
        PlaceDatabase::class.java,
        PlaceDatabase.DATABASE_NAME
    ).build()

    fun getNearbyPlaces(latitude : Double, longitude : Double): LiveData<PlaceSet> {
        DebugLogger.logger("Trying to decode location: $latitude,$longitude")
        val place = placeDatabase.getPlacesDAO().getFromLocation(latitude, longitude)
        refresh(place, latitude, longitude)
        return place
    }

    private fun refresh(liveData: LiveData<PlaceSet>, latitude: Double, longitude : Double) {
        Thread {
            // with live data I can't actually check the cache! This is terrible design.
            // Oh well.
            if(liveData.value == null) {
                DebugLogger.elogger("We got no db value!")
                val response = PlaceRetrofit.getNearbyPlaces(latitude, longitude).execute()
                if (response.isSuccessful) {
                    DebugLogger.logger("We got body: ${response.body()}")
                    response.body()?.also {
                        placeDatabase.getPlacesDAO().insertPlaces(onSuccess(it))
                    }
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