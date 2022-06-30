package com.example.practicaltest.app.presentation.newsfeed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.domain.usecase.NewsUseCase
import com.example.practicaltest.core.extenstion.setError
import com.example.practicaltest.core.extenstion.setLoading
import com.example.practicaltest.core.extenstion.setSuccess
import com.example.practicaltest.core.util.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val liveDataLatestNews = MutableLiveData<Resource<List<DArticle>>>()


    fun getLatestNews(){
        liveDataLatestNews.setLoading()
        compositeDisposable.add(
            newsUseCase.getLatestNews()
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataLatestNews.setSuccess(it, null)
                },{
                    liveDataLatestNews.setError(it.message)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}