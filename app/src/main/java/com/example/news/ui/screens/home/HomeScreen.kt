package com.example.news.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.news.R
import com.example.news.ui.navigation.graph.HomeNavGraph
import com.example.news.ui.navigation.screen.BottomBarScreen

@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = !sharedViewModel.isInternetConnected.collectAsState(true).value,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.onError,
                        text = stringResource(id = R.string.no_internet_connection)
                    )
                }
            }
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        HomeNavGraph(
            navController = navController,
            paddingValues = innerPadding
        )
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Categories,
        BottomBarScreen.Bookmark,
        BottomBarScreen.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val visible = screens.any { it.route == currentDestination?.route }
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(expandFrom = Alignment.Bottom),
        exit = shrinkVertically(shrinkTowards = Alignment.Bottom)
    ) {
        BottomAppBar(
            actions = {
                screens.forEach { screen ->
                    AddItem(
                        navController = navController,
                        currentDestination = currentDestination,
                        screen = screen
                    )
                }
            },
        )
    }
}

@Composable
private fun RowScope.AddItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
    screen: BottomBarScreen
) {
    NavigationBarItem(
        icon = { Icon(screen.icon, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
