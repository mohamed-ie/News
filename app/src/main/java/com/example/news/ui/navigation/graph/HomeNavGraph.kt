package com.example.news.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.utils.getParentViewModel
import com.example.news.ui.navigation.screen.BottomBarScreen
import com.example.news.ui.screens.home.bookmarks.view.BookmarksScreen
import com.example.news.ui.screens.home.categories.CategoriesScreen
import com.example.news.ui.screens.home.home.view.HomeScreen
import com.example.news.ui.screens.home.settings.view.SettingsScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route,
        navController = navController
    ) {

        composable(route = BottomBarScreen.Home.route) { backStackEntry ->
            HomeScreen(
                viewModel = hiltViewModel(),
                sharedViewModel = backStackEntry.getParentViewModel(
                    navController = navController,
                    parentRoute = Graph.HOME
                ),
                navigateTo = { navController.navigate(it) })
        }

        composable(route = BottomBarScreen.Categories.route) {
            CategoriesScreen(navigateTo = { route ->
                navController.navigate(route)
            })
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(viewModel = hiltViewModel(), navigateTo = { route ->
                navController.navigate(route)
            })
        }

        composable(route = BottomBarScreen.Bookmark.route) { backStackEntry ->
            BookmarksScreen(
                viewModel = hiltViewModel(),
                sharedViewModel = backStackEntry.getParentViewModel(
                    navController = navController,
                    parentRoute = Graph.HOME
                ),
                navigateTo = { route ->
                    navController.navigate(route)
                }
            )
        }

        detailsNavGraph(navController)
        searchNavGraph(navController)
    }
}


