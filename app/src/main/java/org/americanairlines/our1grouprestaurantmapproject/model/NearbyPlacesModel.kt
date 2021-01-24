package org.americanairlines.our1grouprestaurantmapproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

/*
 * This is a place to put data our app will actually use (as well as providing a simple caching object.)
 * this class should be modified to reflect the data we want.
 * The primary key is nullable since, well, we cannot guarentee
 */

@Entity
data class NearbyPlacesModel(
        @PrimaryKey(autoGenerate = true) val id : Int?,
        val location : LatLng,
        val vicinity : String,
        val type : String,
        val name : String
)