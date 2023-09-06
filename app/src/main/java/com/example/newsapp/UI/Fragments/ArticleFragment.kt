package com.example.newsapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
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


class ArticleFragment : Fragment(R.layout.article) {

    lateinit var viewModel: NewsViewModel
    lateinit var articlebinding: ArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.article, container, false)

        // Retrieve the article URL from the arguments
        val articleUrl = arguments?.getString("url")

        // Check if the article URL is not null or empty
        if (!articleUrl.isNullOrEmpty()) {
            val webView: WebView = rootView.findViewById(R.id.webView)

            // Set up WebViewClient to handle page loading and errors
            webView.webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    // Handle error display a toast
                    Toast.makeText(
                        context,
                        "Error loading the web page. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Hide the progress indicator when the page is fully loaded
                    rootView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
            }

            // Load the article URL into the WebView
            webView.loadUrl(articleUrl)
        }

        return rootView
    }
    }




