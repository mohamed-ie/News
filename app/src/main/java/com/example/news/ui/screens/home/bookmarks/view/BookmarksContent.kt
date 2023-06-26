package com.example.news.ui.screens.home.bookmarks.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.data.local.db.entity.Article
import com.example.news.ui.common.component.min_article_card.MinArticleCard

@Composable
fun BookmarksContent(
    articles: List<Article>,
    openArticle: (article: Article) -> Unit,
) {
    LazyColumn {
        item {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = stringResource(id = R.string.bookmarks),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        items(articles) { article ->
            MinArticleCard(
                article = article,
                onClick = { openArticle(article) })
        }
    }
}

