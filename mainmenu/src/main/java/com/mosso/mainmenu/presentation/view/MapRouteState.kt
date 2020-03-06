package com.mosso.mainmenu.presentation.view

import com.mosso.mainmenu.domain.models.RouteDomain

sealed class MapRouteState {
    class DisplayMapRoute(val routeDomain: RouteDomain) : MapRouteState()
    class ShowMessage(val message: String) : MapRouteState()
}