package com.mosso.tracerouter

import android.app.Application
import com.mosso.mainmenu.presentation.di.mapRouteModule
import com.mosso.shared.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TraceRouteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TraceRouteApp)
            modules(arrayListOf(coreModule, mapRouteModule))
        }
    }
}