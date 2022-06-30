package com.example.practicaltest.core.util

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val totalResults: Int? = null,
    val message: String? = null,
)