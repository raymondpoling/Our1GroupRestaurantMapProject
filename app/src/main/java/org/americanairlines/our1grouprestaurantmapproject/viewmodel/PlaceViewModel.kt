package org.americanairlines.our1grouprestaurantmapproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.repository.PlaceResultRepository
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.logger

class PlaceViewModel: ViewModel() {

    private val placeResultRepository = PlaceResultRepository()

    fun getSearchResults(latitude: Double, longitude : Double) : LiveData<List<NearbyPlacesModel>>{
        logger("Requesting live data in getSearchResults")
        return placeResultRepository.getNearbyPlaces(latitude, longitude)
    }

    override fun onCleared() {
        super.onCleared()
    }
}