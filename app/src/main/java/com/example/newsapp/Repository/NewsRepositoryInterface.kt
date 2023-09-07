package com.example.newsapp.Repository

import com.example.newsapp.Models.NewsResponse
import retrofit2.Response

interface NewsRepositoryInterface {

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int): Response<NewsResponse>
    suspend fun searchNews (searchQuery:String,pageNumber: Int) : Response<NewsResponse>
}