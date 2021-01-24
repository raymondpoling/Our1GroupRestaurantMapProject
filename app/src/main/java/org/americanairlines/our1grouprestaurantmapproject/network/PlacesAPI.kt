package org.americanairlines.our1grouprestaurantmapproject.network

import io.reactivex.Observable
import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResponse
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.API_KEY
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.LOCATION
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.PLACES_PATH
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.RADIUS
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

    @GET(PLACES_PATH)
    fun getNearbyPlaces(@Query(API_KEY) api_key : String,
                       @Query(LOCATION) location:String,
                       @Query(RADIUS) radius : Long = 500) : Observable<PlaceResponse>
}