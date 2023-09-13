package com.example.newsapp.composables


import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController


@Composable
fun ArticlePage(articleUrl: String?, navController: NavHostController) {


    Column(modifier = Modifier.fillMaxSize()) {
        // Header with button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Article Details", // Replace with your desired header text
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    // Navigate to the SearchNews fragment here
                    navController.navigate("SearchNews")
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Text(text = "Go to Search News")
            }
        }

        // WebView below the header
        if (articleUrl != null) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                factory = { context ->
                    WebView(context).apply {
                        val settings = settings
                        settings.javaScriptEnabled = true
                        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    }
                },
                update = { webView ->
                    webView.loadUrl(articleUrl)
                }
            )
        } else {
            Text(
                text = "Error Loading the WebPage. Please Try again Later",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}


