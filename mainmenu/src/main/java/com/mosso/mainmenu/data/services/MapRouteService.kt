package com.mosso.mainmenu.data.services

import com.mosso.mainmenu.data.models.RouteData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MapRouteService {
    @GET("/maps/api/directions/json?")
    fun getGoogleRouteServices(
        @Query("mode") mode: String,
        @Query("transit_routing_preference") transitRoutingPreference: String,
        @Query("origin") origin: String,
        @Query("destination") destination:String,
        @Query("key") key: String = "AIzaSyBYP3ytubHO1k3B9G9zEnux_j-FQQXY6os"
        ): Single<RouteData>
}