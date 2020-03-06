package com.mosso.mainmenu.domain.remote

import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.presentation.models.RouteRequest
import io.reactivex.Single

interface RemoteSource {
    fun getGoogleRoute(routeRequest: RouteRequest): Single<RouteDomain>
}