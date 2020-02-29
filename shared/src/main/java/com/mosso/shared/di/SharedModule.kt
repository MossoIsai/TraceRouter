package com.mosso.shared.di

import com.mosso.shared.constants.Constants
import com.mosso.shared.domain.executor.PostExecutionThread
import com.mosso.shared.domain.executor.ThreadExecutor
import com.mosso.shared.job.JobExecutor
import com.mosso.shared.job.UIThread
import com.mosso.shared.remote.ApiServiceFactory
import com.mosso.shared.remote.ServiceFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideOkHttp(): OkHttpClient {

    val httpLoginInterceptor = HttpLoggingInterceptor()
    httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoginInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.URL_BASE)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiServiceFactory(retrofit: Retrofit): ServiceFactory =
    ApiServiceFactory(retrofit)

private fun provideThreadExecutor(): ThreadExecutor = JobExecutor()

private fun providePostExecutionThread(): PostExecutionThread = UIThread()

val coreModule = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { provideApiServiceFactory(get()) }
    single { provideThreadExecutor() }
    single { providePostExecutionThread() }
}