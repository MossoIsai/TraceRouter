package com.mosso.mainmenu.data.mappers

import com.google.android.gms.maps.model.LatLng
import com.mosso.mainmenu.data.models.Polyline
import com.mosso.mainmenu.data.models.RouteData
import com.mosso.mainmenu.data.models.Steps
import com.mosso.mainmenu.data.models.Value
import com.mosso.mainmenu.domain.models.Points
import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.shared.data.Transform

class RouteMapper(
    private val stepsToPoints: Transform<Steps, Points>
) : Transform<RouteData, RouteDomain>() {
    override fun transform(value: RouteData): RouteDomain {
        return RouteDomain(
            value.routes ?: arrayListOf(),
            value.routes?.firstOrNull()?.legs?.firstOrNull()?.startAddress.toString(),
            value.routes?.firstOrNull()?.legs?.firstOrNull()?.endAddress.toString(),
            LatLng(
                value.routes?.firstOrNull()?.legs?.firstOrNull()?.startLocation?.lat ?: 0.0,
                value.routes?.firstOrNull()?.legs?.firstOrNull()?.startLocation?.lng ?: 0.0
            ),
            LatLng(
                value.routes?.firstOrNull()?.legs?.firstOrNull()?.endLocation?.lat ?: 0.0,
                value.routes?.firstOrNull()?.legs?.firstOrNull()?.endLocation?.lng ?: 0.0
            ),
            value.routes?.firstOrNull()?.legs?.firstOrNull()?.distance ?: Value("", 0.0),
            value.routes?.firstOrNull()?.legs?.firstOrNull()?.distance ?: Value("", 0.0),
            value.routes?.firstOrNull()?.legs?.firstOrNull()?.steps?.let {
                stepsToPoints.transformCollection(
                    it
                )
            } as MutableList<Points>,
            value.routes.firstOrNull()?.overviewPolyline?.points.toString()
        )

    }

    class StepsToOriginLocation : Transform<Steps, Points>() {
        override fun transform(value: Steps): Points {
            return Points(
                LatLng(value.startLocation?.lat ?: 0.0, value.startLocation?.lng ?: 0.0),
                LatLng(value.endLocation?.lat ?: 0.0, value.endLocation?.lng ?: 0.0)
            )
        }
    }

}