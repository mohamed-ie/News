package com.example.news.ui.screens.details.all_articles.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.data.local.db.entity.Article
import com.example.news.ui.common.component.min_article_card.MinArticleCard
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AllArticlesContent(
    state: AllArticlesState,
    openArticle: (article: Article) -> Unit,
    onEvent: (AllArticlesEvent) -> Unit,
    back: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            query = state.searchQuery,
            onQueryChange = { onEvent(AllArticlesEvent.SearchQueryChanged(it)) },
            onSearch = {
                keyboardController?.hide()
                onEvent(AllArticlesEvent.Search)
            },
            active = true,
            onActiveChange = {},
            placeholder = { Text(stringResource(id = R.string.search)) },
            leadingIcon = {
                IconButton(modifier = Modifier.padding(end = 8.dp), onClick = back) {
                    Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(onClick = { onEvent(AllArticlesEvent.Search) }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        ) {
            val listState = rememberLazyListState()

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(state.articlesData.articles) { article ->
                    MinArticleCard(article = article, onClick = { openArticle(article) })
                }


                item {
                    AnimatedVisibility(visible = state.isLoadMoreVisible) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = { onEvent(AllArticlesEvent.LoadMore) }
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
}


@Preview
@Composable
fun P() {
    NewsTheme {
        AllArticlesContent(state = AllArticlesState(), openArticle = {}, back = {}, onEvent = {})
    }
}

