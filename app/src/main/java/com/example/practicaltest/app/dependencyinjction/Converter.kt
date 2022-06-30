package com.example.practicaltest.app.dependencyinjction

import com.example.practicaltest.apiclient.apiSupport.model.Article
import com.example.practicaltest.apiclient.apiSupport.model.Source
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.domain.model.DSource


fun Article.mapToDomain(): DArticle = DArticle(
    source = source?.mapToDomain(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)


fun Source.mapToDomain(): DSource = DSource(
    sourceId = sourceId,
    sourceName = sourceName
)