package com.mosso.shared.domain


import com.mosso.shared.domain.executor.PostExecutionThread
import com.mosso.shared.domain.executor.ThreadExecutor
import com.mosso.shared.domain.usecase.UseCase
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Params, T>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) : UseCase<Params, Single<T>>() {

    fun execute(params: Params? = null): Single<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }

    fun execute(params: Params?, observer: DisposableSingleObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        subscribe(observable.subscribeWith(observer))
    }
}
