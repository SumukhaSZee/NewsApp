package com.example.newsapp.api


import com.example.newsapp.BuildConfig.API_KEY
import com.example.newsapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode : String,
        @Query("page") pageNumber : Int,
        @Query("apiKey") apiKey:String = API_KEY
    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery : String,
        @Query("page") pageNumber : Int,
        @Query("apiKey") apiKey:String = API_KEY
    ):Response<NewsResponse>
}