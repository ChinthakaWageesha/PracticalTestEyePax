package com.example.practicaltest.apiclient.apis

import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {

    @GET("everything")
    fun getNewsFeed(
    ): Single<NewsResponse>
}