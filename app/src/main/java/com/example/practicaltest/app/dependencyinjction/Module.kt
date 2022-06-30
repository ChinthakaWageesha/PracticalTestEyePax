package com.example.practicaltest.app.dependencyinjction

import com.example.practicaltest.app.data.datasource.NewsDataSource
import com.example.practicaltest.app.data.repository.NewsRepositoryImpl
import com.example.practicaltest.app.domain.repository.NewsRepository
import com.example.practicaltest.app.domain.usecase.NewsUseCase
import com.example.practicaltest.app.presentation.newsfeed.NewsViewModel
import com.example.practicaltest.app.remotedatasource.NewsDataSourceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        NewsViewModel(
            newsUseCase = get()
        )
    }
}

val useCaseModule: Module = module(override = true) {
    factory { NewsUseCase(newsRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        NewsRepositoryImpl(
            newsDataSource = get()
        ) as NewsRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        NewsDataSourceImpl(
            newsApi = get()
        ) as NewsDataSource
    }
}