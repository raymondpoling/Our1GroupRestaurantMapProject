package org.americanairlines.our1grouprestaurantmapproject.model

data class NearbyPlacesResponse(
    val html_attributions: List<Any>,
    val results: List<Result>,
    val status: String
)