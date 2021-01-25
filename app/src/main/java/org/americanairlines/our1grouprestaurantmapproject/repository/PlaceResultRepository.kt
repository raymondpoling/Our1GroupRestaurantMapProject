package org.americanairlines.our1grouprestaurantmapproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import io.reactivex.disposables.CompositeDisposable
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.data.PlaceDatabase
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.elogger
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.logger
import org.americanairlines.our1grouprestaurantmapproject.view.ListNearbyPlacesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

typealias PlaceSet = List<NearbyPlacesModel>

class PlaceResultRepository(val placeLiveData : MutableLiveData<PlaceSet>) {

    private val placeDatabase: PlaceDatabase = Room.databaseBuilder(
        ListNearbyPlacesActivity.getContext(),
        PlaceDatabase::class.java,
        PlaceDatabase.DATABASE_NAME
    ).build()

    fun getNearbyPlaces(latitude : Double, longitude : Double) {
        DebugLogger.logger("Trying to decode location: $latitude,$longitude")
        Thread {
            val place = placeDatabase.getPlacesDAO().getFromLocation(latitude, longitude)
            if (place.isEmpty()) {
                refresh(placeLiveData, latitude, longitude)
            } else {
                placeLiveData.postValue(place)
            }
        }.start()
    }

    private fun refresh(placeLiveData: MutableLiveData<PlaceSet>, latitude: Double, longitude : Double) {
            // with live data I can't actually check the cache! This is terrible design.
            // Oh well.
            DebugLogger.elogger("We got no db value!")
            val response = PlaceRetrofit.getNearbyPlaces(latitude, longitude).enqueue(object : Callback<PlaceResponse> {
                override fun onResponse(call: Call<PlaceResponse>, response: Response<PlaceResponse>) {
                    Thread {
                        if (response.isSuccessful) {
                            DebugLogger.logger("We got body: ${response.body()}")
                            response.body()?.also {
                                val ret = onSuccess(it)
                                placeDatabase.getPlacesDAO().insertPlaces(ret)
                                placeLiveData.postValue(ret)
                            }
                        }
                    }.start()

                }

                override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                    elogger("Error in retrofit call: ${t.message}")
                }
            })
    }


    private fun onSuccess(placeResponse: PlaceResponse) : List<NearbyPlacesModel> =
                placeResponse.results.map { t ->
                    logger("trying to create ${t} entry")
                    t.let {
                        NearbyPlacesModel(
                                it.geometry.location.lat,
                                it.geometry.location.lng,
                                it.vicinity?:"",
                                it.types?.first()?:"",
                                it.name?:"")
                    }
                }

    private fun onFailure(t: Throwable) : Unit {
        DebugLogger.elogger("Received an error: ${t.message.toString()}")
    }


}