package com.mosso.mainmenu.data.remote

import com.mosso.mainmenu.data.mappers.RouteMapper
import com.mosso.mainmenu.data.services.MapRouteService
import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.domain.remote.RemoteSource
import com.mosso.mainmenu.presentation.models.RouteRequest
import com.mosso.shared.job.exceptions.UseCaseException
import io.reactivex.Single

class RemoteDataSource(
    private val routeService: MapRouteService,
    private val mapper: RouteMapper
) : RemoteSource {
    override fun getGoogleRoute(routeRequest: RouteRequest): Single<RouteDomain> {
        return routeService.getGoogleRouteServices(
            routeRequest.mode,
            routeRequest.transitRouting,
            setLocationFormat(routeRequest.latitudeOrigin, routeRequest.longitudeOrigin),
            setLocationFormat(routeRequest.latitudeDestination, routeRequest.longitudeDestination),
            API_KEY
        ).onErrorResumeNext { error ->
            return@onErrorResumeNext Single.error(error)
        }.map { response ->
            when (response.status) {
                REQUEST_SUCCESS ->
                    return@map mapper.transform(response)
                REQUEST_DENIED ->
                    throw  UseCaseException.GenericException(
                        "The provided API key is invalid."
                    )
                else ->
                    throw UseCaseException.GenericException(
                        response.errorMessage.toString()
                    )
            }
        }
    }

    private fun setLocationFormat(latitude: Double, longitude: Double): String =
        "$latitude,$longitude"

    companion object {
        const val REQUEST_SUCCESS = "OK"
        const val REQUEST_DENIED = "REQUEST_DENIED"
        const val API_KEY = "AIzaSyBYP3ytubHO1k3B9G9zEnux_j-FQQXY6os"
    }

}