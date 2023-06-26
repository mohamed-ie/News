package com.example.news.ui.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val icon: ImageVector) {
    object Home : BottomBarScreen(
        route = "HOME",
        icon = Icons.Rounded.Home
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        icon = Icons.Rounded.Settings
    )

    object Categories : BottomBarScreen(
        route = "CATEGORIES",
        icon = Icons.Rounded.Category
    )

    object Bookmark : BottomBarScreen(
        route = "SAVED",
        icon = Icons.Rounded.Book
    )
}
