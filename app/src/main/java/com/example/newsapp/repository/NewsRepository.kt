package com.example.newsapp.repository

import com.example.newsapp.api.NewsApi
class NewsRepository(
    private val api:NewsApi
):NewsRepositoryInterface {
    override suspend fun getBreakingNews(countryCode:String, pageNumber:Int)=
        api.getBreakingNews(countryCode,pageNumber)

    override suspend fun searchNews (searchQuery:String, pageNumber: Int) =
        api.searchForNews(searchQuery,pageNumber)

}