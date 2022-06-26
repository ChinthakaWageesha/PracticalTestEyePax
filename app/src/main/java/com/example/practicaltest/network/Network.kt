package com.example.practicaltest.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

fun createNetworkClient(
    baseUrl: String,
    supportInterceptor: SupportInterceptor
) =
    retrofitClient(baseUrl, httpClient(supportInterceptor))

private fun httpClient(
    supportInterceptor: SupportInterceptor
): OkHttpClient {

    val clientBuilder = OkHttpClient.Builder()

    clientBuilder.authenticator(supportInterceptor)
    clientBuilder.addInterceptor(supportInterceptor)

    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()