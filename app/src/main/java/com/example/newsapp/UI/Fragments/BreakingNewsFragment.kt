package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.Adapters.OnItemClickListener
import com.example.newsapp.Models.Article
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.Util.Resource
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.databinding.BrNewsBinding



class BreakingNewsFragment :Fragment(R.layout.br_news),OnItemClickListener {


    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    lateinit var brNewsBinding: BrNewsBinding
    var TAG = "BreakingNewsFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        brNewsBinding = BrNewsBinding.inflate(inflater, container, false)
        brNewsBinding.lifecycleOwner = viewLifecycleOwner // for fragments
        val view = brNewsBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        setupRecyclerView()

       /* newsAdapter.setOnItemClickListener { it ->
            it?.let { article ->
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(
                    R.id.action_breakingNewsFragment2_to_articleFragment2,
                    bundle
                )
            }
        }*/






        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success->{
                    hideprogressbar()
                    response.data?.let{newsResponse ->
                         val filteredArticles = newsResponse.articles.filterNotNull()
                         newsAdapter.differ.submitList(filteredArticles)

                    }
                }
                is Resource.Error->{
                    hideprogressbar()
                    response.message?.let{message->
                        Log.d(TAG,"An Error Occured ${message}")

                    }
                }
                is Resource.Loading->{
                    showprogressbar()
                }
            }

        })

    }

    private fun hideprogressbar() {
         brNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showprogressbar() {
         brNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
            newsAdapter = NewsAdapter(this)
            brNewsBinding.rvBreakingNews.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
            }
        }

    override fun onItemClick(article: Article) {
        // Ensure that the 'article' object is not null before using it
        if (article != null) {
            val bundle = Bundle().apply {
                putSerializable("article", article)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment2_to_articleFragment2,
                bundle
            )
        } else {
            // Handle the case where 'article' is null (optional)
            // You can log an error message or take appropriate action here
            Log.d(TAG,"Error in receiving article")
            Toast.makeText(context, "Article is null", Toast.LENGTH_SHORT).show()
        }
    }

}





