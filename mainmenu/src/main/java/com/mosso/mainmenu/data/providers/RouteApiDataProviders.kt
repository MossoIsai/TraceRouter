package com.mosso.mainmenu.data.providers

import com.mosso.mainmenu.data.mappers.RouteMapper
import com.mosso.mainmenu.data.remote.RemoteDataSource
import com.mosso.mainmenu.data.repositories.GetRouteDataRepository
import com.mosso.mainmenu.data.services.MapRouteService
import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.domain.providers.RouteApiProvider
import com.mosso.mainmenu.domain.usecases.GetRouteUseCase
import com.mosso.mainmenu.presentation.models.RouteRequest
import com.mosso.shared.domain.SingleUseCase
import com.mosso.shared.domain.executor.PostExecutionThread
import com.mosso.shared.domain.executor.ThreadExecutor
import com.mosso.shared.remote.ServiceFactory

class RouteApiDataProviders(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    serviceFactory: ServiceFactory
) : RouteApiProvider {

    private val service =
        serviceFactory.makeConnectionApiService(MapRouteService::class.java)
    private val repository = GetRouteDataRepository(
        RemoteDataSource(
            service,
            RouteMapper(RouteMapper.StepsToOriginLocation())
        )
    )

    override fun attemptGetGoogleRoutes(): SingleUseCase<RouteRequest, RouteDomain> {
        return GetRouteUseCase(threadExecutor, postExecutionThread, repository)
    }

}