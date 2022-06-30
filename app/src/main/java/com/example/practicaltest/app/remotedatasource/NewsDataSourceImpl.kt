package com.example.practicaltest.app.remotedatasource

import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import com.example.practicaltest.apiclient.apis.NewsApi
import com.example.practicaltest.app.data.datasource.NewsDataSource
import com.example.practicaltest.app.dependencyinjction.mapToDomain
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.core.util.Constant
import io.reactivex.Single

class NewsDataSourceImpl(
    private val newsApi: NewsApi
) : NewsDataSource {

    override fun getLatestNews(): Single<List<DArticle>> =
        newsApi.getLatestNews(Constant.API_KEY, "en").map {
            it.data?.map {
                it.mapToDomain()
            }
        }

    override fun getNews(searchKey: String?): Single<NewsResponse> =
        newsApi.getNewsFeed(Constant.API_KEY, searchKey,"en").map {
            it
        }


}