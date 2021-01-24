package org.americanairlines.our1grouprestaurantmapproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity
data class NearbyPlacesModel(
        @PrimaryKey(autoGenerate = true) val id : Int?,
        val location : LatLng,
        val vicinity : String,
        val type : String,
        val name : String
)