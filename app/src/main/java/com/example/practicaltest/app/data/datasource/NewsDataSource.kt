package com.example.practicaltest.app.data.datasource

import com.example.practicaltest.app.domain.model.DArticle
import io.reactivex.Single

interface NewsDataSource {

    fun getLatestNews(): Single<List<DArticle>>

}