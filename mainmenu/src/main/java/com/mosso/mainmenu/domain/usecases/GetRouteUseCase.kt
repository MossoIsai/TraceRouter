package com.mosso.mainmenu.domain.usecases

import com.mosso.mainmenu.domain.models.RouteDomain
import com.mosso.mainmenu.domain.repositories.MapRouteRepository
import com.mosso.mainmenu.presentation.models.RouteRequest
import com.mosso.shared.domain.SingleUseCase
import com.mosso.shared.domain.executor.PostExecutionThread
import com.mosso.shared.domain.executor.ThreadExecutor
import com.mosso.shared.job.exceptions.UseCaseException
import io.reactivex.Single

class GetRouteUseCase(
    threadExecution: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repository: MapRouteRepository
) : SingleUseCase<RouteRequest, RouteDomain>(threadExecution, postExecutionThread) {

    override fun buildUseCase(params: RouteRequest?): Single<RouteDomain> {
        return params?.let {
            repository.attemptGetRoute(params)
        } ?: Single.error(UseCaseException.GenericException())
    }
}