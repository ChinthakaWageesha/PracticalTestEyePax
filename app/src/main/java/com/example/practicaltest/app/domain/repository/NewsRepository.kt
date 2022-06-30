package com.example.practicaltest.app.domain.repository

import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import com.example.practicaltest.app.domain.model.DArticle
import io.reactivex.Single

interface NewsRepository {

    fun getLatestNews(): Single<List<DArticle>>

    fun getNews(searchKey: String?): Single<NewsResponse>

}