package com.example.newsapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Api.RetrofitInstance
import com.example.newsapp.Models.Article
import com.example.newsapp.Models.NewsResponse
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.Util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsrepository : NewsRepository
): ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1


    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    init{
        getBreakingNews("us")
    }

    /*fun getDataforfirstFragment(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = RetrofitInstance.api.getBreakingNews("us",breakingNewsPage)
            Log.d("APIResponse1",result?.body()?.articles.toString())

            val Articles : NewsResponse? = result.body()
            Log.d("total pages",Articles?.articles.toString())

            breakingNews.postValue(Articles?.articles)
        }
    }*/

    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsrepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch{
        searchNews.postValue(Resource.Loading())
        val response = newsrepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }

            }
        return Resource.Error(response.message())
        }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())
    }

    }
