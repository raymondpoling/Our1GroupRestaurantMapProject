package org.americanairlines.our1grouprestaurantmapproject.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.google.android.gms.maps.model.LatLng
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.NEAR
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.NEARBY_PLACES

@Dao
interface PlaceDAO {
    
    @Insert(onConflict = REPLACE)
    fun insertPlaces(places: List<NearbyPlacesModel>)

    @Query("SELECT * " +
            "FROM $NEARBY_PLACES " +
            "WHERE latitude > :latitude - $NEAR " +
            "AND latitude < :latitude + $NEAR " +
            "AND longitude > :longitude - $NEAR " +
            "AND longitude < :longitude  + $NEAR"
    )
    fun getFromLocation(latitude: Double, longitude : Double) : LiveData<List<NearbyPlacesModel>>

    @Delete
    fun deletePlace(place: NearbyPlacesModel) : Unit


}