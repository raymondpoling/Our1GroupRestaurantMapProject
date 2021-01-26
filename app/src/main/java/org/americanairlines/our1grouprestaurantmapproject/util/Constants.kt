package org.americanairlines.our1grouprestaurantmapproject.util

import com.google.android.gms.maps.model.LatLng

class Constants {

    // Example URL: https://maps.googleapis.com/maps/api/place/nearbysearch/output?parameters
    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
        const val API_KEY = "key"
        const val PLACES_PATH = "maps/api/place/nearbysearch/json?rankby=distance"

        // parameter arguments for PLACES_PATH
        const val LOCATION = "location"
        const val RADIUS = "radius"

        // what ROOM should consider to be `near'
        const val NEAR : Double = 0.05

        // room variable
        const val NEARBY_PLACES : String = "nearby_places"
    }
}