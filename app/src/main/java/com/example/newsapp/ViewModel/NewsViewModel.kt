package com.example.newsapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Models.NewsResponse
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.Repository.NewsRepositoryInterface
import com.example.newsapp.Util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsrepository : NewsRepositoryInterface
): ViewModel() {


    private val breakingNews1 = MutableStateFlow<Resource<NewsResponse>>(Resource.Loading())
    val breakingNews :StateFlow<Resource<NewsResponse>> = breakingNews1
    val breakingNewsPage = 1


    private val searchNews1 = MutableStateFlow<Resource<NewsResponse>>(Resource.Loading())
    val searchNews : StateFlow<Resource<NewsResponse>> = searchNews1
    val searchNewsPage = 1

    init{
        getBreakingNews("us")
    }
    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews1.value= Resource.Loading()
        val response = newsrepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews1.value = handleBreakingNewsResponse(response)
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch{
        searchNews1.value = Resource.Loading()
        val response = newsrepository.searchNews(searchQuery,searchNewsPage)
        searchNews1.value = handleSearchNewsResponse(response)

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
