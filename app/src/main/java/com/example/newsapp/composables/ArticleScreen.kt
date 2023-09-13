package com.example.newsapp.composables


import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsapp.ViewModel.NewsViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun ArticlePage(articleUrl: String?) {

    if (articleUrl != null) {
        WebViewScreen(url = articleUrl)
    }


    // ... Use the loaded article to display details in your UI ...
}


@Composable
fun WebViewScreen(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                val settings = settings
                settings.javaScriptEnabled = true
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}