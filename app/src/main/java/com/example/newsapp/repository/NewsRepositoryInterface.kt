package com.example.newsapp.repository

import com.example.newsapp.models.NewsResponse
import retrofit2.Response

interface NewsRepositoryInterface {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int): Response<NewsResponse>
    suspend fun searchNews (searchQuery:String,pageNumber: Int) : Response<NewsResponse>
}