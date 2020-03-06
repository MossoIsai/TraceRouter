package com.mosso.mainmenu.data.models

import com.google.gson.annotations.SerializedName

data class RouteData(
    @SerializedName("geocoded_waypoints") val wayPoints: MutableList<WayPoints>? = null,
    @SerializedName("routes") val routes: MutableList<Route>? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("error_message") val errorMessage: String? = null
)

data class WayPoints(
    @SerializedName("geocoder_status") val geoCoderStatus: String? = null,
    @SerializedName("place_id") val placeId: String? = null,
    @SerializedName("types") val types: MutableList<String>? = null
)

data class Route(
    @SerializedName("bounds") val bounds: Bounds? = null,
    @SerializedName("copyrights") val copyrights: String? = null,
    @SerializedName("legs") val legs:MutableList<Legs>? = null,
    @SerializedName("overview_polyline") val overviewPolyline: OverviewPolyline? = null,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("warning") val warnings: MutableList<String>? = null,
    @SerializedName("waypoint_order") val wayPointOrder: MutableList<String>? = null
)

data class Bounds(
    @SerializedName("northeast") val northeast: Location? = null,
    @SerializedName("southwest") val southwest: Location? = null
)

data class Location(
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lng") val lng: Double? = null
)

data class Legs(
    @SerializedName("distance") val distance: Value? = null,
    @SerializedName("duration") val duration: Value? = null,
    @SerializedName("end_address") val endAddress: String? = null,
    @SerializedName("end_location") val endLocation: Location? = null,
    @SerializedName("start_address") val startAddress: String? = null,
    @SerializedName("start_location") val startLocation: Location? = null,
    @SerializedName("steps") val steps: MutableList<Steps>? = null,
    @SerializedName("traffic_speed_entry") val trafficSpeedEntry: MutableList<String>? = null,
    @SerializedName("via_waypoint") val viaWayPoint: MutableList<String>? = null
)

data class OverviewPolyline(
    @SerializedName("points") val points: String? = null
)

data class Value(
    @SerializedName("text") val text: String? = null,
    @SerializedName("value") val value: Double? = null
)

data class Steps(
    @SerializedName("distance") val distance: Value? = null,
    @SerializedName("duration") val duration: Value? = null,
    @SerializedName("end_location") val endLocation: Location? = null,
    @SerializedName("html_instructions") val htmlInstructions: String? = null,
    @SerializedName("polyline") val polyline: Polyline? = null,
    @SerializedName("start_location") val startLocation: Location? = null,
    @SerializedName("travel_mode") val travelMode: String? = null
)

data class Polyline(
    @SerializedName("points") val points: String? = null
)