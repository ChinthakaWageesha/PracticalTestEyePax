package com.example.practicaltest.network

import com.example.practicaltest.apiclient.apis.NewsApi
import com.example.practicaltest.core.util.Constant
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val supportInterceptor = SupportInterceptor()

private val retrofit: Retrofit =
    createNetworkClient(Constant.BASE_URL, supportInterceptor)

val apiModule: Module = module {
    single { supportInterceptor }
    single { newsApi }
}

private val newsApi: NewsApi = retrofit.create(NewsApi::class.java)