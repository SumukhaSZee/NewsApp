package com.example.newsapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.Models.Article
import com.example.newsapp.R



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            ,
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
           GlideImage(model = article.urlToImage,
                contentDescription ="Load Image",
                contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium),
            alignment = Alignment.Center){
                it.error(R.drawable.ic_launcher_foreground)
                it.placeholder(R.drawable.ic_launcher_foreground)
                it.load(article.urlToImage)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.source.toString(),
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            article?.description?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Published At: ${article.publishedAt}",
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

