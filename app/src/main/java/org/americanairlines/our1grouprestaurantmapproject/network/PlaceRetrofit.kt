package org.americanairlines.our1grouprestaurantmapproject.network

import android.content.res.Resources
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.BASE_URL
import org.americanairlines.our1grouprestaurantmapproject.view.TestAPIFragment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object PlaceRetrofit {

    private val apiKey : String by lazy {
        TestAPIFragment.context.getString(R.string.google_maps_key)
    }

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val placesAPI : PlacesAPI = retrofit.create(PlacesAPI::class.java)

    fun getNearbyPlaces(location: LatLng) : Observable<PlaceResponse> {
        return placesAPI.getNearbyPlaces(apiKey, location.paramString())
    }

    // extension function to define how LatLng is to be printed in calls
    private fun LatLng.paramString() : String = "${this.latitude},${this.longitude}"

}