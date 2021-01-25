package org.americanairlines.our1grouprestaurantmapproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import org.americanairlines.our1grouprestaurantmapproject.util.Constants.Companion.NEARBY_PLACES

/*
 * This is a place to put data our app will actually use (as well as providing a simple caching object.)
 * this class should be modified to reflect the data we want.
 * The primary key is nullable since only room, not our application, should be setting it.
 */

@Entity(tableName = NEARBY_PLACES, primaryKeys = ["latitude","longitude"])
data class NearbyPlacesModel(
        @ColumnInfo(name = "latitude")
        var latitude : Double,
        @ColumnInfo(name = "longitude")
        var longitude : Double,
        @ColumnInfo(name = "vicinity")
        var vicinity : String,
        @ColumnInfo(name = "location_type")
        var locationType : String,
        @ColumnInfo(name = "place_name")
        var name : String
)