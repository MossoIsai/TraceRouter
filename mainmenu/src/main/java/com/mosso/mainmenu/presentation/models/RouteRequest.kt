package com.mosso.mainmenu.presentation.models

data class RouteRequest(
    val latitudeOrigin: Double,
    val longitudeOrigin: Double,
    val latitudeDestination: Double,
    val longitudeDestination: Double,
    val mode: String,
    val transitRouting: String,
    val key: String
)