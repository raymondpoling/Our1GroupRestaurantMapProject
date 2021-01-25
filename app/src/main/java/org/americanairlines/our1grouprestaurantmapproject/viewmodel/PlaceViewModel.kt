package org.americanairlines.our1grouprestaurantmapproject.viewmodel

import androidx.lifecycle.LiveData
<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit
import org.americanairlines.our1grouprestaurantmapproject.repository.PlaceResultRepository
=======
import androidx.lifecycle.ViewModel
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.repository.PlaceResultRepository
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.logger
>>>>>>> ef9e5a4ddeb13ae6c193a2d7488d04e51f607a3b

class PlaceViewModel : ViewModel() {

<<<<<<< HEAD
//    val placeLiveData: MutableLiveData<List<PlaceResult>> = MutableLiveData()
//    private val placeRetrofit: PlaceRetrofit = PlaceRetrofit
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//    private lateinit var nearbyLiveData: LiveData<List<NearbyPlacesModel>>
//    private var placeResultRepository: PlaceResultRepository = PlaceResultRepository()
//
//
//    fun getNearbyPlaces(location: LatLng): LiveData<List<NearbyPlacesModel>> {
//
//       nearbyLiveData = placeResultRepository.getNearbyPlaces(location)
//        return nearbyLiveData
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//    }
=======
    private val placeResultRepository = PlaceResultRepository()

    fun getSearchResults(latitude: Double, longitude : Double) : LiveData<List<NearbyPlacesModel>>{
        logger("Requesting live data in getSearchResults")
        return placeResultRepository.getNearbyPlaces(latitude, longitude)
    }

    override fun onCleared() {
        super.onCleared()
    }
>>>>>>> ef9e5a4ddeb13ae6c193a2d7488d04e51f607a3b
}