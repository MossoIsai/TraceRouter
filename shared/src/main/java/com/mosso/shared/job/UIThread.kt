package com.mosso.shared.job

import com.mosso.shared.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}