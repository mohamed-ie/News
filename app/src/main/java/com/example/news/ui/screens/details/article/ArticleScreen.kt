package com.example.news.ui.screens.details.article

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.news.ui.common.LoadingScreen
import com.example.news.ui.screens.details.article.component.ArticleLoading
import com.example.news.ui.screens.home.SharedViewModel
import com.example.news.utils.ScreenState

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel,
    sharedViewModel: SharedViewModel,
    back: () -> Unit,
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.setArticle(sharedViewModel.article)
    })
    val state by viewModel.state.collectAsState()

    when (viewModel.screenState) {
        ScreenState.Error -> {}
        ScreenState.Loading -> LoadingScreen()
        ScreenState.Stable ->  ArticleContent(state, back, viewModel::onEvent)
    }
}

