package com.example.newsapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.Models.Article
import com.example.newsapp.R



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
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.source.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.description,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Published At: ${article.publishedAt}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

