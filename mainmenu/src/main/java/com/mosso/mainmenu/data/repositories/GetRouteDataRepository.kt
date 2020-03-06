package com.mosso.mainmenu.data.repositories

import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.domain.remote.RemoteSource
import com.mosso.mainmenu.domain.repositories.MapRouteRepository
import com.mosso.mainmenu.presentation.models.RouteRequest
import io.reactivex.Single

class GetRouteDataRepository(
    private val remoteSource: RemoteSource
) : MapRouteRepository {

    override fun attemptGetRoute(routeRequest: RouteRequest): Single<RouteDomain> {
       return remoteSource.getGoogleRoute(routeRequest)
    }


}