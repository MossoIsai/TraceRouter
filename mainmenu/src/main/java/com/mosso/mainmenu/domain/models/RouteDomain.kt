package com.mosso.mainmenu.domain.models

import com.google.android.gms.maps.model.LatLng
import com.mosso.mainmenu.data.models.Route
import com.mosso.mainmenu.data.models.Value

data class RouteDomain(
    val routes: MutableList<Route>,
    val originAddress: String,
    val destinationAddress: String,
    val originLocation: LatLng,
    val destinationLocation: LatLng,
    val distance: Value,
    val duration: Value,
    val points: MutableList<Points>,
    val polyline: String
)
