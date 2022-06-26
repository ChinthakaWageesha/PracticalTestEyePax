package com.example.practicaltest.apiclient.apiSupport.model

import com.squareup.moshi.Json

data class Source(
    @Json(name = "id") @field:Json(name = "id") var sourceId: String? = null,
    @Json(name = "name") @field:Json(name = "name") var sourceName: String? = null
)
