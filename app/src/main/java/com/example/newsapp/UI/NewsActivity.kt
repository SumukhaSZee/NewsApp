
package com.example.newsapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.composables.ArticlePage
import com.example.newsapp.composables.BreakingNews
import com.example.newsapp.composables.SearchNews

import com.google.android.material.bottomnavigation.BottomNavigationView


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
        NavHost(navController, startDestination ="breakingNews"){
            composable("breakingNews"){
                BreakingNews()
            }
            composable("SearchNews"){
                SearchNews()
            }
            composable("ArticlePage"){
                ArticlePage()
            }
        }

}

