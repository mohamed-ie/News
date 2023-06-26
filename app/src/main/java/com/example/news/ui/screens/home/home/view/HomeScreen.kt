package com.example.news.ui.screens.home.home.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.news.ui.common.LoadingScreen
import com.example.news.ui.navigation.graph.Graph
import com.example.news.ui.screens.home.SharedViewModel
import com.example.news.ui.screens.home.home.HomeViewModel
import com.example.news.utils.ScreenState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateTo: (String) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    val state by viewModel.state.collectAsState()

    when (viewModel.screenState) {
        is ScreenState.Error -> {}

        ScreenState.Loading -> LoadingScreen()

        ScreenState.Stable -> HomeContent(
            state = state,
            openArticle = {
                sharedViewModel.setArticle(it)
                navigateTo(Graph.DETAILS)
            },
            loadMore = viewModel::loadMore,
            navigateTo = navigateTo,
        )
    }


}

