package com.example.practicaltest.apiclient.apiSupport.response

import com.example.practicaltest.apiclient.apiSupport.model.Article
import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "status") @field:Json(name = "status") var status: String? = null,
    @Json(name = "totalResults") @field:Json(name = "totalResults") var totalResults: Int? = null,
    @Json(name = "articles") @field:Json(name = "articles") var data: List<Article>? = null,
)