package com.example.news.ui.screens.details.all_articles.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.news.ui.common.LoadingScreen
import com.example.news.ui.navigation.screen.DetailsScreen
import com.example.news.ui.screens.details.all_articles.AllArticlesViewModel
import com.example.news.ui.screens.home.SharedViewModel
import com.example.news.utils.ScreenState

@Composable
fun AllArticlesScreen(
    viewModel: AllArticlesViewModel,
    sharedViewModel: SharedViewModel,
    navigateTo: (String) -> Unit,
    back: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    when (viewModel.screenState) {
        is ScreenState.Error -> {}
        ScreenState.Loading -> LoadingScreen()
        ScreenState.Stable -> AllArticlesContent(
            state = state,
            openArticle = { article ->
                sharedViewModel.setArticle(article)
                navigateTo(DetailsScreen.ARTICLE_DETAILS)
            },
            onEvent = viewModel::onEvent,
            back = back
        )
    }
}

