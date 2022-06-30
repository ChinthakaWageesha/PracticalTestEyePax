package com.example.practicaltest.core.extenstion

import androidx.lifecycle.MutableLiveData
import com.example.practicaltest.core.util.Resource
import com.example.practicaltest.core.util.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T, totalResults: Int?, message: String?) =
    postValue(Resource(ResourceState.SUCCESS,  data, totalResults, message))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(
        Resource(
            ResourceState.LOADING,
            value?.data
        )
    )

fun <T> MutableLiveData<Resource<T>>.setError(message: String?) =
    postValue(
        Resource(
            ResourceState.ERROR,
            value?.data,
            0,
            message
        )
    )
