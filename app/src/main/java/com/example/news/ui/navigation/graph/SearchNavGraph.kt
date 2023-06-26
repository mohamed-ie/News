package com.example.news.ui.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.news.ui.navigation.screen.SearchScreen
import com.example.news.utils.getParentViewModel
import com.example.news.ui.screens.search.view.SearchScreen

fun NavGraphBuilder.searchNavGraph(navController: NavHostController) {
    navigation(route = Graph.SEARCH, startDestination = SearchScreen.Search.route) {
        composable(route = SearchScreen.Search.route) { backStackEntry ->
            SearchScreen(
                viewModel = hiltViewModel(),
                sharedViewModel = backStackEntry.getParentViewModel(
                    navController = navController,
                    parentRoute = Graph.HOME
                ),
                navigateTo = { navController.navigate(it) },
                back = { navController.popBackStack() }
            )
        }
    }
}