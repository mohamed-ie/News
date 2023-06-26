package com.example.news.ui.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object DetailsScreen {
    const val ARTICLE_DETAILS = "article"

    object AllArticles : DestinationWithArgs(
        route = "article/{category}",
        routeToBeFormatted = "article/%s",
        args = listOf(navArgument("category") {
            type = NavType.StringType
        })
    )
}
