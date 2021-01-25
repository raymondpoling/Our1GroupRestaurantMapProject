package org.americanairlines.our1grouprestaurantmapproject.network

import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.BASE_URL
import org.americanairlines.our1grouprestaurantmapproject.view.ListNearbyPlacesActivity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PlaceRetrofit {

    private val apiKey : String by lazy<String> {
//        TestAPIFragment.context.getString(R.string.google_maps_key)
        ListNearbyPlacesActivity.getContext().getString(R.string.google_maps_key)
    }

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val placesAPI : PlacesAPI = retrofit.create(PlacesAPI::class.java)

    fun getNearbyPlaces(latitide : Double, longitude : Double) : Call<PlaceResponse> {
        return placesAPI.getNearbyPlaces(apiKey, "$latitide,$longitude")
    }

}