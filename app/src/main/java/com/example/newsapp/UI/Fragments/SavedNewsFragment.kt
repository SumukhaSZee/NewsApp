package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
/*import com.example.newsapp.DataBase.ArticleDatabase*/
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.databinding.BrNewsBinding
import com.example.newsapp.databinding.SavedNewsBinding

class SavedNewsFragment :Fragment(R.layout.saved_news){

    lateinit var viewModel: NewsViewModel
    lateinit var savedNewsBinding: SavedNewsBinding
    lateinit var newsAdapter : NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedNewsBinding= SavedNewsBinding.inflate(inflater,container,false)   // for fragments
        val view = savedNewsBinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository= NewsRepository(/*ArticleDatabase.createDatabase(requireContext())*/)
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        setupRecyclerView()

       /* newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment2_to_articleFragment2,
                bundle
            )
        }*/
        newsAdapter.setOnItemClickListener { it ->
            it?.let { article ->
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(
                    R.id.action_savedNewsFragment2_to_articleFragment2,
                    bundle
                )
            }
        }


    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        savedNewsBinding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
        }
    }
}