package com.mosso.mainmenu.domain.models

import com.google.android.gms.maps.model.LatLng

data class Points(
    val starPoint: LatLng,
    val endPoint: LatLng
)