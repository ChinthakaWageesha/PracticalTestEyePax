package com.example.practicaltest.app.data.repository

import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import com.example.practicaltest.app.data.datasource.NewsDataSource
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.domain.repository.NewsRepository
import io.reactivex.Single

class NewsRepositoryImpl constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepository {

    override fun getLatestNews(): Single<List<DArticle>> =
        newsDataSource.getLatestNews()

    override fun getNews(searchKey: String?): Single<NewsResponse> =
        newsDataSource.getNews(searchKey)
}