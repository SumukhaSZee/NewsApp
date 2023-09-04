package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
/*import com.example.newsapp.DataBase.ArticleDatabase*/
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.Util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.newsapp.Util.Resource
import com.example.newsapp.databinding.BrNewsBinding
import com.example.newsapp.databinding.SearchResultsBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment :Fragment(R.layout.search_results) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    lateinit var searchNewsBinding: SearchResultsBinding


    val TAG = "searchNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchNewsBinding = SearchResultsBinding.inflate(inflater,container,false)   // for fragments
        val view = searchNewsBinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        super.onViewCreated(view, savedInstanceState)



        val newsRepository= NewsRepository(/*ArticleDatabase.createDatabase(requireContext())*/)
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]


        setupRecyclerView()

        /*newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment2_to_articleFragment2,
                bundle
            )
        }*/

        newsAdapter.setOnItemClickListener { it ->
            it?.let { article ->
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(
                    R.id.action_searchNewsFragment2_to_articleFragment2,
                    bundle
                )
            }
        }

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


        viewModel.searchNews.observe(viewLifecycleOwner,  Observer {response->
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
        searchNewsBinding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun  showProgressBar(){
        searchNewsBinding.paginationProgressBar.visibility = View.VISIBLE
    }

   private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        searchNewsBinding.rvSearchNews.apply{
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
            }
    }
}