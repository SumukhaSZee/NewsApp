package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.Adapters.NewsAdapter
/*import com.example.newsapp.DataBase.ArticleDatabase*/
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.databinding.ArticleBinding
import com.example.newsapp.databinding.SavedNewsBinding

class ArticleFragment :Fragment(R.layout.article) {

    lateinit var viewModel: NewsViewModel


    lateinit var articlebinding: ArticleBinding
    lateinit var newsAdapter : NewsAdapter
    val args : ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       articlebinding = ArticleBinding.inflate(inflater,container,false)   // for fragments
        val view = articlebinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository= NewsRepository(/*ArticleDatabase.createDatabase(requireContext())*/)
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        val article = args.article
        articlebinding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }

}