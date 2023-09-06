package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.Adapters.OnItemClickListener
import com.example.newsapp.Models.Article
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.Util.Constants.SEARCH_NEWS_TIME_DELAY
import com.example.newsapp.Util.Resource
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.databinding.SearchResultsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchNewsFragment :Fragment(R.layout.search_results),OnItemClickListener {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    lateinit var searchNewsBinding: SearchResultsBinding


    val TAG = "searchNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchNewsBinding = SearchResultsBinding.inflate(inflater,container,false)
        searchNewsBinding.lifecycleOwner = viewLifecycleOwner // for fragments
        val view = searchNewsBinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]
        setupRecyclerView()

        var job : Job? = null
        searchNewsBinding.etSearch.addTextChangedListener {editable->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let{
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }

            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.searchNews.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        withContext(Dispatchers.Main){
                            hideProgressBar()
                            response.data?.let { newsResponse ->
                                val filteredArticles = newsResponse.articles.filterNotNull()
                                newsAdapter.differ.submitList(filteredArticles)
                            }
                        }

                    }
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            hideProgressBar()
                            response.message?.let { message ->
                                Log.d(TAG, "An Error Occured $message")
                            }
                        }

                    }
                    is Resource.Loading -> {
                        withContext(Dispatchers.Main){
                            showProgressBar()
                        }

                    }
                }
            }
        }

    }

    private fun hideProgressBar(){
        searchNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun  showProgressBar(){
        searchNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }

   private fun setupRecyclerView(){
        newsAdapter = NewsAdapter(this)
        searchNewsBinding.rvSearchNews.apply{
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
            }
    }

    override fun onItemClick(article: Article) {
        // Ensure that the 'article' object is not null before using it
        if (article != null) {
            val bundle = Bundle()
            bundle.putString("url",article.url)
            findNavController().navigate(
                R.id.action_searchNewsFragment2_to_articleFragment2,
                bundle
            )
        } else {
            Log.d(TAG,"Error in receiving article")
            Toast.makeText(context, "Article is null", Toast.LENGTH_SHORT).show()
        }
    }
}