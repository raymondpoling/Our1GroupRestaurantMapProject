package org.americanairlines.our1grouprestaurantmapproject.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.google.android.gms.maps.model.LatLng
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.NEAR
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.NEARBY_PLACES

@Dao
interface PlaceDAO {
    
    @Insert(onConflict = IGNORE)
    fun insertPlaces(places: List<NearbyPlacesModel>)

    @Query("SELECT * " +
            "FROM $NEARBY_PLACES " +
            "WHERE ABS(:lat - latitude) < $NEAR " +
            "AND ABS(:long - longitude) < $NEAR " +
            "ORDER BY ABS(:lat - latitude) + ABS(:long - longitude) ASC"
    )
    fun getFromLocation(lat: Double, long : Double) : List<NearbyPlacesModel>

    @Delete
    fun deletePlace(place: NearbyPlacesModel) : Unit


}