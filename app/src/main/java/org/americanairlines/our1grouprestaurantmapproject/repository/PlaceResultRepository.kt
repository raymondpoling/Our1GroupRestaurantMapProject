package org.americanairlines.our1grouprestaurantmapproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger

class PlaceResultRepository {

    private val cd = CompositeDisposable()

    private val nearbyLiveData : MutableLiveData<List<NearbyPlacesModel>> = MutableLiveData()

    fun getNearbyPlaces(location : LatLng) : LiveData<List<NearbyPlacesModel>> {
        // todo plug in ROOM database
        cd.add(PlaceRetrofit.getNearbyPlaces(location)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onSuccess,
                        this::onFailure))
        return nearbyLiveData
    }

    private fun onSuccess(placeResponse: PlaceResponse) : Unit {
        nearbyLiveData.postValue(
                placeResponse.results.map {t ->
                    NearbyPlacesModel(null,
                    t.geometry.location.toLatLng(),
                    t.vicinity,
                    t.types.first(),
                    t.name)})
        cd.clear()
    }

    private fun onFailure(t : Throwable) : Unit {
        DebugLogger.elogger("Received an error: ${t.message.toString()}")
    }


}