
package com.example.newsapp.ui

import android.net.Uri
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
import com.example.newsapp.composables.ArticlePage
import com.example.newsapp.composables.BreakingNews
import com.example.newsapp.composables.SearchNews



class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsApp()
        }
    }
}

@Composable
fun NewsApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "breakingNews") {

        composable("breakingNews") {
            BreakingNews {
                Log.d("OnItemView", it)

                val routeWithQueryParams = "ArticlePage?articleUrl=${Uri.encode(it)}"
                navController.navigate(routeWithQueryParams)
            }
        }

        composable("SearchNews") {
            SearchNews { searchText ->
                val routeWithQueryParams = "ArticlePage?articleUrl=${Uri.encode(searchText)}"
                navController.navigate(routeWithQueryParams)
            }
        }

        composable(
            route = "ArticlePage?articleUrl={articleUrl}",
            arguments = listOf(
                navArgument("articleUrl") {
                    type = NavType.StringType
                }
            )
        ) {
            val articleUrl = it.arguments?.getString("articleUrl")
            ArticlePage(articleUrl,navController)
        }
    }
}


