package org.americanairlines.our1grouprestaurantmapproject.model.googleapi

import androidx.room.Entity


@Entity
data class PlaceResult(
        val geometry: Geometry,
        val icon: String?,
        val id: String?,
        val name: String?,
        val opening_hours: OpeningHours?,
        val photos: List<Photo>?,
        val place_id: String?,
        val reference: String?,
        val types: List<String>?,
        val vicinity: String?
)