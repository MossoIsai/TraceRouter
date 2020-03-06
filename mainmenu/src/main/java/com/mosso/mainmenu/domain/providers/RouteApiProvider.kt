package com.mosso.mainmenu.domain.providers

import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.presentation.models.RouteRequest
import com.mosso.shared.domain.SingleUseCase

interface RouteApiProvider {
    fun attemptGetGoogleRoutes(): SingleUseCase<RouteRequest, RouteDomain>
}