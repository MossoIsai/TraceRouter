package com.mosso.mainmenu.domain.repositories

import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.presentation.models.RouteRequest
import io.reactivex.Single

interface MapRouteRepository {
    fun attemptGetRoute(routeRequest: RouteRequest): Single<RouteDomain>
}