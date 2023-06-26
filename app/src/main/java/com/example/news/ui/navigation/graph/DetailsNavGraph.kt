package com.example.news.ui.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.news.ui.navigation.screen.DetailsScreen
import com.example.news.utils.getParentViewModel
import com.example.news.ui.screens.details.all_articles.view.AllArticlesScreen
import com.example.news.ui.screens.details.article.ArticleScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(route = Graph.DETAILS, startDestination = DetailsScreen.ARTICLE_DETAILS) {
        composable(route = DetailsScreen.ARTICLE_DETAILS) { backStackEntry ->
            ArticleScreen(
                viewModel = hiltViewModel(),
                sharedViewModel = backStackEntry.getParentViewModel(
                    navController = navController,
                    parentRoute = Graph.HOME
                ),
                back = { navController.popBackStack() },
            )
        }

        composable(
            route = DetailsScreen.AllArticles.route,
            arguments = DetailsScreen.AllArticles.args
        ) { backStackEntry ->
            AllArticlesScreen(
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