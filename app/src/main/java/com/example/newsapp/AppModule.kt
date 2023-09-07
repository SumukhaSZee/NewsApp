package com.example.newsapp

import com.example.newsapp.Api.NewsApi
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.Repository.NewsRepositoryInterface
import com.example.newsapp.Util.Constants.BASE_URL
import com.example.newsapp.ViewModel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    single <NewsRepositoryInterface> {
        NewsRepository(get())
    }
    viewModel{
        NewsViewModel(get())
    }
}