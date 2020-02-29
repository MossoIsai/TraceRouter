package com.mosso.shared.remote

import retrofit2.Retrofit

class ApiServiceFactory(
    private val retrofit: Retrofit
) : ServiceFactory {

    override fun <T> makeConnectionApiService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}