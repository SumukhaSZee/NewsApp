package com.example.newsapp.Repository

import com.example.newsapp.Api.NewsApi
class NewsRepository(
    private val api:NewsApi
):NewsRepositoryInterface {
    override suspend fun getBreakingNews(countryCode:String, pageNumber:Int)=
        api.getBreakingNews(countryCode,pageNumber)

    override suspend fun searchNews (searchQuery:String, pageNumber: Int) =
        api.searchForNews(searchQuery,pageNumber)

}