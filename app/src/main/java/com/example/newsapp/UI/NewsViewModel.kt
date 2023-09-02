package com.example.newsapp.UI

import androidx.lifecycle.ViewModel
import com.example.newsapp.Repository.NewsRepository

class NewsViewModel(
    val newsrepository : NewsRepository
): ViewModel() {
}