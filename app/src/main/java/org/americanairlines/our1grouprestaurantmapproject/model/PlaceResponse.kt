package org.americanairlines.our1grouprestaurantmapproject.model

data class PlaceResponse(
    val html_attributions: List<Any>,
    val results: List<PlaceResult>,
    val status: String
)