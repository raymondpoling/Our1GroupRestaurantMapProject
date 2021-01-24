package org.americanairlines.our1grouprestaurantmapproject.network

import android.content.Context
import retrofit2.Retrofit

class NearbyPlacesRetrofit {

    companion object {
        private var nearbyPlacesService : NearbyPlacesService? = null
    }

    fun getInstance() : NearbyPlacesService {
        return nearbyPlacesService?:{
            nearbyPlacesService = NearbyPlacesService()
            nearbyPlacesService
        }()
    }

    private fun getRetrofit() : Retrofit = Retrofit.Builder().baseUrl(BASE_URL).
}