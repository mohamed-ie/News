package com.example.news.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.utils.getParentViewModel
import com.example.news.ui.screens.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        route = Graph.ROOT,
        navController = navController,
        startDestination = Graph.LANDING
    ) {
        landingNavGraph(navController)

        composable(Graph.HOME) {
            HomeScreen(it.getParentViewModel(navController = navController, parentRoute = Graph.HOME))
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val LANDING = "landing_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
    const val SEARCH = "search_graph"
}