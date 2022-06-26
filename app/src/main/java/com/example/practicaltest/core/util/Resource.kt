package com.example.practicaltest.core.util

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val error: String? = null
)