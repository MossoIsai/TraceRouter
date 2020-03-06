package com.mosso.mainmenu.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mosso.mainmenu.domain.providers.RouteApiProvider
import com.mosso.mainmenu.domain.resources.MapRouteResources
import com.mosso.mainmenu.presentation.view.MapRouteState
import com.mosso.mainmenu.presentation.models.RouteRequest
import com.mosso.shared.view.ScreenState
import io.reactivex.disposables.CompositeDisposable

class MapViewModel(
    private val apiProvider: RouteApiProvider,
    private val resource: MapRouteResources
) : ViewModel() {
    private var disposable = CompositeDisposable()
    private var responseState: MutableLiveData<ScreenState<MapRouteState>> = MutableLiveData()


    val mapRouteState: LiveData<ScreenState<MapRouteState>>
        get() = responseState

    fun getGoogleRoute(
        originLatitude: Double?,
        originLongitude: Double?,
        destinationLatitude: Double?,
        destinationLongitude: Double?,
        mode: String,
        transitRouting: String,
        key: String
    ) {
        val routeRequest: RouteRequest?

        if (originLatitude != null && originLongitude != null &&
            destinationLatitude != null && destinationLongitude != null
        ) {
            routeRequest = RouteRequest(
                originLatitude,
                originLongitude,
                destinationLatitude,
                destinationLongitude,
                mode,
                transitRouting,
                key
            )
            disposable.add(
                apiProvider.attemptGetGoogleRoutes()
                    .execute(routeRequest)
                    .doOnSubscribe {
                        responseState.value = ScreenState.Loading
                    }.subscribe({ route ->
                        responseState.value =
                            ScreenState.Render(MapRouteState.DisplayMapRoute(route))
                    }, {
                        responseState.value =
                            ScreenState.Render(MapRouteState.ShowMessage(it.message.toString()))
                    })

            )
        } else {
            responseState.value =
                ScreenState.Render(MapRouteState.ShowMessage(resource.getValidateCoordinatesMessage()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }
}