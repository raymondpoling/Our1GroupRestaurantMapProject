package org.americanairlines.our1grouprestaurantmapproject.model.googleapi

data class PlaceResponse(
        val html_attributions: List<Any>?,
        val results: List<PlaceResult>,
        val status: String
)