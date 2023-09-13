
package com.example.newsapp.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.util.Constants.SEARCH_NEWS_TIME_DELAY
import com.example.newsapp.util.Resource
import com.example.newsapp.viewmodel.NewsViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun SearchNews(onClick : (url:String)->Unit) {
        val viewModel = getViewModel<NewsViewModel>()
        var searchText = remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(8.dp)
                    .height(48.dp) // Adjust the height as needed
                    .align(Alignment.Start), // Align to the top

                placeholder = {
                    Text(
                        text = "Search",
                        color = Color.Black,
                    )
                },
                singleLine = true, // Allow only a single line of text
                shape = RoundedCornerShape(16.dp) // Apply rounded corners
            )



            val searchResultsResource = viewModel.searchNews.collectAsState().value
            Log.d("result",searchResultsResource.toString())

            when (searchResultsResource) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    val articles = searchResultsResource.data?.articles
                    LazyColumn {
                        if(articles!=null){
                            items(articles) { article ->
                                Log.d("results1",articles.toString())
                                ArticleItem(article,onClick)
                            }
                        }

                    }
                }
                is Resource.Error -> {
                    Text(text = "Search for some News Articles")

                }
            }
        }
    LaunchedEffect(searchText.value) {
        delay(SEARCH_NEWS_TIME_DELAY)
        if (searchText!=null) {
            viewModel.searchNews(searchText.value)
        }
    }


    }



