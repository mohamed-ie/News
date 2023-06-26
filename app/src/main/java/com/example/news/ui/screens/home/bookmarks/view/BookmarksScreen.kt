package com.example.news.ui.screens.home.bookmarks.view

import androidx.compose.runtime.Composable
import com.example.news.ui.common.LoadingScreen
import com.example.news.ui.navigation.graph.Graph
import com.example.news.ui.screens.home.SharedViewModel
import com.example.news.ui.screens.home.bookmarks.BookmarksViewModel
import com.example.news.utils.ScreenState

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel,
    sharedViewModel: SharedViewModel,
    navigateTo: (String) -> Unit
) {
    when (viewModel.screenState) {
        ScreenState.Error -> {}
        ScreenState.Loading -> LoadingScreen()
        ScreenState.Stable -> BookmarksContent(
            articles = viewModel.articles,
            openArticle = {
                sharedViewModel.setArticle(it)
                navigateTo(Graph.DETAILS)
            },
        )
    }
}