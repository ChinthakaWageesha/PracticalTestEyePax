package com.example.practicaltest.apiclient.apiSupport.model

import com.squareup.moshi.Json

data class Article(
    @Json(name = "source") @field:Json(name = "source") var source: Source? = null,
    @Json(name = "author") @field:Json(name = "author") var author: String? = null,
    @Json(name = "title") @field:Json(name = "title") var title: String? = null,
    @Json(name = "description") @field:Json(name = "description") var description: String? = null,
    @Json(name = "url") @field:Json(name = "url") var url: String? = null,
    @Json(name = "urlToImage") @field:Json(name = "urlToImage") var urlToImage: String? = null,
    @Json(name = "publishedAt") @field:Json(name = "publishedAt") var publishedAt: String? = null,
    @Json(name = "content") @field:Json(name = "content") var content: String? = null
)
