package com.mosso.shared.remote

interface ServiceFactory {
    fun <T> makeConnectionApiService(serviceClass: Class<T>): T
}