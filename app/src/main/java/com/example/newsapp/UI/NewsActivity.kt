
package com.example.newsapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.composables.ArticlePage
import com.example.newsapp.composables.BreakingNews
import com.example.newsapp.composables.SearchNews

import org.koin.androidx.compose.getViewModel



class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApp()
        }
    }
}

@Composable
fun NewsApp(){
        val navController = rememberNavController()
        val viewModel = getViewModel<NewsViewModel>()
        NavHost(navController, startDestination ="breakingNews"){
            composable("breakingNews"){
                BreakingNews {
                    navController.navigate("ArticlePage/$it" )
                }
            }
            composable("SearchNews"){
                SearchNews{
                    navController.navigate("ArticlePage/$it" )
                }
            }

            composable(
                route = "ArticlePage/{articleUrl}",
                arguments = listOf(navArgument("articleUrl") {
                    type = NavType.StringType}
                )) {
                val articleUrl = it.arguments?.getString("articleUrl")
                Log.d("hello",articleUrl.toString())
                    ArticlePage(articleUrl)

            }
        }

}

