package org.americanairlines.our1grouprestaurantmapproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.repository.PlaceResultRepository
import org.americanairlines.our1grouprestaurantmapproject.repository.PlaceSet
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.logger

class PlaceViewModel: ViewModel() {

    private val placesLiveData = MutableLiveData<PlaceSet>()

    private val placeResultRepository = PlaceResultRepository(placesLiveData)

    fun getSearchResults(latitude: Double, longitude : Double) : LiveData<List<NearbyPlacesModel>>{
        logger("Requesting live data in getSearchResults")
        placeResultRepository.getNearbyPlaces(latitude, longitude)
        return placesLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}