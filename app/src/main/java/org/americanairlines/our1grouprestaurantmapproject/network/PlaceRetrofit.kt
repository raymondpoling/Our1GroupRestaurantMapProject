package org.americanairlines.our1grouprestaurantmapproject.network

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object PlaceRetrofit {

    private val okHttpClient = {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    }()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val placesAPI : PlacesAPI = retrofit.create(PlacesAPI::class.java)

    fun getNearbyPlaces(apiKey: String, location: LatLng, radius: Long) : Observable<PlaceResponse> {
        return placesAPI.getNearbyPlaces(apiKey, location.paramString(), radius)
    }

    // extension function to define how LatLng is to be printed in calls
    private fun LatLng.paramString() : String = "${this.latitude},${this.longitude}"

}