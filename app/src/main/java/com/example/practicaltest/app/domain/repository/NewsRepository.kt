package com.example.practicaltest.app.domain.repository

import com.example.practicaltest.app.domain.model.DArticle
import io.reactivex.Single

interface NewsRepository {

    fun getLatestNews(): Single<List<DArticle>>

}