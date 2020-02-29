package com.mosso.shared.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler

}