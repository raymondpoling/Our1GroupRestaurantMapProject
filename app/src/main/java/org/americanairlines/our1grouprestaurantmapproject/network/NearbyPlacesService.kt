package org.americanairlines.our1grouprestaurantmapproject.network

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesResponse

interface NearbyPlacesService {
    fun getNearbyLocations(latLong: LatLng, range: Int = 10) : Observable<NearbyPlacesResponse>

}