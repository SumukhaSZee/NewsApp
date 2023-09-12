package com.example.newsapp.composables



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.newsapp.Util.Resource
import com.example.newsapp.ViewModel.NewsViewModel
import org.koin.androidx.compose.getViewModel




@Composable
fun BreakingNews(){
    val viewModel = getViewModel<NewsViewModel>()
    val resource = viewModel.breakingNews.collectAsState().value

    when (resource) {
        is Resource.Loading -> {
            // Display a loading indicator or a placeholder
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            val articles = resource.data?.articles
            LazyColumn {
                if(articles!=null){
                    items(articles) { article ->
                        // Display individual articles as before
                        ArticleItem(article)
                    }
                }

            }
        }
        is Resource.Error -> {
            // Display an error message or handle the error as needed
            Text(text = "Error: ${resource.message}")
        }
    }
}



