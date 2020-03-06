package com.mosso.mainmenu.presentation.resources

import android.content.Context
import com.mosso.mainmenu.R
import com.mosso.mainmenu.domain.resources.MapRouteResources

class RouteResource(private val context: Context) : MapRouteResources {

    override fun getValidateCoordinatesMessage(): String =
        context.getString(R.string.coordinate_wrong_error_message)
}