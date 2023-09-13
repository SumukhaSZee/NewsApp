package com.example.newsapp.composables
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.newsapp.util.Resource
import com.example.newsapp.viewmodel.NewsViewModel
import org.koin.androidx.compose.getViewModel
@Composable
fun BreakingNews(onClick : (url:String)->Unit) {
    val viewModel = getViewModel<NewsViewModel>()
    val resource = viewModel.breakingNews.collectAsState().value
    when (resource) {
        is Resource.Loading -> {

            CircularProgressIndicator()
        }
        is Resource.Success -> {
            val articles = resource.data?.articles
            LazyColumn {
                if(articles!=null){
                    items(articles) { article ->
                        ArticleItem(article,onClick)
                    }
                }

            }
        }
        is Resource.Error -> {

            Text(text = "Error: ${resource.message}")
        }
    }
}



