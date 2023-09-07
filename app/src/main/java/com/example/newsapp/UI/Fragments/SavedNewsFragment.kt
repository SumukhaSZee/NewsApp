package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.Adapters.OnItemClickListener
import com.example.newsapp.Models.Article
import com.example.newsapp.R
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.databinding.SavedNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedNewsFragment :Fragment(R.layout.saved_news),OnItemClickListener{

    private val viewModel by viewModel<NewsViewModel>()
    lateinit var savedNewsBinding: SavedNewsBinding
    lateinit var newsAdapter : NewsAdapter
    var TAG = "SavedNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedNewsBinding= SavedNewsBinding.inflate(inflater,container,false)
        savedNewsBinding.lifecycleOwner = viewLifecycleOwner // for fragments
        val view = savedNewsBinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter(this)
        savedNewsBinding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
        }
    }

    override fun onItemClick(article: Article) {
        // Ensure that the 'article' object is not null before using it
        if (article != null) {
            val bundle = Bundle()
            bundle.putString("url",article.url)
            findNavController().navigate(
                R.id.action_savedNewsFragment2_to_articleFragment2,
                bundle
            )
        } else {
            Log.d(TAG,"Error in receiving article")
            Toast.makeText(context, "Article is null", Toast.LENGTH_SHORT).show()
        }
    }
}