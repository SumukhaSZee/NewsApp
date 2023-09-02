package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.UI.NewsActivity
import com.example.newsapp.UI.NewsViewModel

class ArticleFragment :Fragment(R.layout.article) {

    lateinit var viewModel:NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }

}