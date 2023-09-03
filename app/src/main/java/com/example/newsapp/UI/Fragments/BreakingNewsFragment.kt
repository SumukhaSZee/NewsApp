package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.DataBase.ArticleDatabase
import com.example.newsapp.Models.Article
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.Util.Resource
import com.example.newsapp.databinding.ArticlePreviewBinding
import com.example.newsapp.databinding.BrNewsBinding

class BreakingNewsFragment :Fragment(R.layout.br_news) {

    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    lateinit var brNewsBinding: BrNewsBinding
    var TAG = "BreakingNewsFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        brNewsBinding = BrNewsBinding.inflate(inflater,container,false)   // for fragments
        val view = brNewsBinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val newsRepository = NewsRepository(ArticleDatabase.createDatabase(requireContext()))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]


        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            prepareRecyclerView(it)
        })


        }

    fun prepareRecyclerView(results : List<Article>)
    {
        Log.d(TAG,"hello from rv")
        brNewsBinding.rvBreakingNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
        val recycleAdapter = NewsAdapter(results)
        brNewsBinding.rvBreakingNews.adapter = recycleAdapter
    }

    private fun hideProgressBar() {
        brNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        brNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }


    }




