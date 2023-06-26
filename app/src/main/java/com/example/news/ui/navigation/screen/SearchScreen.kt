package com.example.news.ui.navigation.screen

sealed class SearchScreen(val route: String) {
    object Search : SearchScreen(route = "search")
}
