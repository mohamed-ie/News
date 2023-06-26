package com.example.news.ui.screens.home.home.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.news.R
import com.example.news.data.local.db.entity.Article
import com.example.news.ui.navigation.graph.Graph
import com.example.news.ui.screens.home.home.view.component.ArticleCard
import com.example.news.ui.common.component.min_article_card.MinArticleCard
import com.example.news.ui.screens.details.all_articles.view.AllArticlesEvent
import kotlin.math.absoluteValue

@Composable
fun HomeContent(
    state: HomeState,
    loadMore: () -> Unit,
    openArticle: (Article) -> Unit,
    navigateTo: (String) -> Unit,
) {
    Scaffold(floatingActionButton = { SearchFloatingActionButton(onClick = { navigateTo(Graph.SEARCH) }) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            item {
                HeadlinesContent(state.topHeadlines,
                    onClick = { openArticle(it) })
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(state.articles) {
                MinArticleCard(
                    article = it,
                    onClick = { openArticle(it) },
                )
            }

            item {
                AnimatedVisibility(visible = state.isLoadMoreVisible) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = loadMore
                    ) {
                        Text(
                            text = stringResource(id = R.string.load_more),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HeadlinesContent(
    topHeadlines: List<Article>,
    onClick: (Article) -> Unit,
) {
    val pagerState = rememberPagerState()
    Text(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        text = stringResource(id = R.string.top_headlines),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )

    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        pageCount = topHeadlines.size,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { page ->
        val article = topHeadlines[page]
        val modifier = Modifier.graphicsLayer {
            val pageOffset = ((pagerState.currentPage - page)
                    + pagerState.currentPageOffsetFraction).absoluteValue

            alpha = lerp(
                start = 0.5f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        }
        ArticleCard(
            modifier = modifier,
            article = article,
            onClick = { onClick(article) }
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    PagerIndicator(topHeadlines.size, pagerState.currentPage)
}

@Composable
private fun PagerIndicator(pageCount: Int, currentPage: Int) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            val width = if (currentPage == iteration) 24.dp else 8.dp
            val animatedSize by animateDpAsState(
                targetValue = width,
                animationSpec = TweenSpec(durationMillis = 1000)
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(width = animatedSize, height = 8.dp)
            )
        }
    }
}

@Composable
private fun SearchFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(Icons.Filled.Search, null)
    }
}

