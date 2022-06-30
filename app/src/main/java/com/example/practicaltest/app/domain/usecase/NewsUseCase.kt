package com.example.practicaltest.app.domain.usecase

import com.example.practicaltest.app.domain.repository.NewsRepository

class NewsUseCase(private val newsRepository: NewsRepository) {

    fun getLatestNews() = newsRepository.getLatestNews()

    fun getNews(searchKey: String?) = newsRepository.getNews(searchKey)

}