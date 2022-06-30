package com.example.practicaltest.core.util

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val status: String? = null,
    val code: String? = null,
    val message: String? = null,
)