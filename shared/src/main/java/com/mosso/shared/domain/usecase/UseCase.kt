package com.mosso.shared.domain.usecase

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, R : Any> {

    // TODO this must be change by io.reactivex.disposables.CompositeDisposable
    private var disposable: Disposable? = null

    protected abstract fun buildUseCase(params: Params? = null): R

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }
}
