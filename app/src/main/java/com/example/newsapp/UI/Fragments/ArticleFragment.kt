package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.Adapters.NewsAdapter
import com.example.newsapp.R
import com.example.newsapp.Repository.NewsRepository
import com.example.newsapp.UI.NewsActivity
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.ViewModel.NewsViewModelProviderFactory
import com.example.newsapp.databinding.ArticleBinding
/*
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
        articlebinding.lifecycleOwner = viewLifecycleOwner
        val view = articlebinding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository= NewsRepository()
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]


        val article = args.article
        articlebinding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }
}
*/

class ArticleFragment : Fragment(R.layout.article) {

    lateinit var viewModel: NewsViewModel
    lateinit var articlebinding: ArticleBinding
    lateinit var newsAdapter: NewsAdapter
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articlebinding = ArticleBinding.inflate(inflater, container, false)
        articlebinding.lifecycleOwner = viewLifecycleOwner
        val view = articlebinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        val article = args.article
        articlebinding.webView.apply {
            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    // Handle error (e.g., display an error message)
                    Toast.makeText(
                        context,
                        "Error loading the web page. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Hide the progress indicator when the page is fully loaded
                    articlebinding.progressBar.visibility = View.GONE
                }
            }
            loadUrl(article.url)
        }
    }
}

