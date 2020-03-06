package com.mosso.mainmenu.presentation.di

import android.content.Context
import com.mosso.mainmenu.data.mappers.RouteMapper
import com.mosso.mainmenu.data.providers.RouteApiDataProviders
import com.mosso.mainmenu.data.remote.RemoteDataSource
import com.mosso.mainmenu.data.repositories.GetRouteDataRepository
import com.mosso.mainmenu.data.services.MapRouteService
import com.mosso.mainmenu.domain.providers.RouteApiProvider
import com.mosso.mainmenu.domain.remote.RemoteSource
import com.mosso.mainmenu.domain.repositories.MapRouteRepository
import com.mosso.mainmenu.domain.resources.MapRouteResources
import com.mosso.mainmenu.domain.usecases.GetRouteUseCase
import com.mosso.mainmenu.presentation.resources.RouteResource
import com.mosso.mainmenu.presentation.viewmodels.MapViewModel
import com.mosso.shared.domain.executor.PostExecutionThread
import com.mosso.shared.domain.executor.ThreadExecutor
import com.mosso.shared.remote.ServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module



fun provideStepsToPoints() = RouteMapper.StepsToOriginLocation()

fun provideRouteMapper(mapper: RouteMapper.StepsToOriginLocation) = RouteMapper(mapper)

fun provideMapRouteResource(context: Context): MapRouteResources =
    RouteResource(context)

fun provideMapRouteRemoteSource(
    mapRouteService: MapRouteService,
    mapper: RouteMapper
) = RemoteDataSource(mapRouteService, mapper)

fun provideMapRouteRepository(remoteSource: RemoteSource) =
    GetRouteDataRepository(remoteSource)

fun provideApiMapRouteProvider(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    serviceFactory: ServiceFactory
) = RouteApiDataProviders(threadExecutor, postExecutionThread, serviceFactory)

fun provideGetMapRouteUserUseCase(
    registerRepository: MapRouteRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) = GetRouteUseCase(threadExecutor, postExecutionThread, registerRepository)

val mapRouteModule = module {
    single { provideStepsToPoints() }
    single { provideRouteMapper(get()) }
    single { provideMapRouteRemoteSource(get(), get()) }
    single { provideMapRouteRepository(get()) }
    single { provideGetMapRouteUserUseCase(get(), get(), get()) }
    single { provideMapRouteResource(androidContext()) }
    single <RouteApiProvider> { provideApiMapRouteProvider(get(), get(), get()) }
    viewModel { MapViewModel(get(), get()) }
}