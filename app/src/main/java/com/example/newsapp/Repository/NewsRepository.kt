package com.example.newsapp.Repository

import com.example.newsapp.Api.RetrofitInstance
import com.example.newsapp.DataBase.ArticleDatabase

class NewsRepository(
    val db :ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews (searchQuery:String,pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

}