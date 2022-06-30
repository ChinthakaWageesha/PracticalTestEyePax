package com.example.practicaltest.apiclient.apis

import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {

    @GET("top-headlines")
    fun getLatestNews(
        @retrofit2.http.Query("apiKey") apiKey: String,
        @retrofit2.http.Query("language") language: String
    ): Single<NewsResponse>


    @GET("everything")
    fun getNewsFeed(
        @retrofit2.http.Query("apiKey") apiKey: String,
        @retrofit2.http.Query("q") searchKey: String?,
        @retrofit2.http.Query("qInTitle") title: String?,
    ): Single<NewsResponse>
}