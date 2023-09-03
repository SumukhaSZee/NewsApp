package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.DataBase.ArticleDatabase
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.UI.NewsActivity
import com.example.newsapp.UI.NewsViewModel
import com.example.newsapp.UI.NewsViewModelProviderFactory
import com.example.newsapp.Util.Resource
import com.example.newsapp.databinding.ArticlePreviewBinding
import com.example.newsapp.databinding.BrNewsBinding

class BreakingNewsFragment :Fragment(R.layout.br_news) {

    lateinit var viewModel:NewsViewModel
    var newsAdapter = NewsAdapter()
    lateinit var binding: ArticlePreviewBinding
    lateinit var brNewsBinding: BrNewsBinding
    var TAG = "BreakingNewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArticlePreviewBinding.inflate(layoutInflater)
        brNewsBinding = BrNewsBinding.inflate(layoutInflater)



        val newsRepository= NewsRepository(ArticleDatabase.createDatabase(requireContext()))
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let{message->
                        Log.e(TAG,"An Error Occured:${message}")

                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }

        })
    }

    private fun  hideProgressBar(){
        brNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun  showProgressBar(){
        brNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        /*newsAdapter = NewsAdapter()
        brNewsBinding.apply {
            rvBreakingNews.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(activity)
            }*/
        brNewsBinding.rvBreakingNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,true)
        val recycleAdapter = NewsAdapter()
        brNewsBinding.rvBreakingNews.adapter = recycleAdapter
        }


    }

