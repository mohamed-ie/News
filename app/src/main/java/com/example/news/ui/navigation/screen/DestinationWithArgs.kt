package com.example.news.ui.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

open class DestinationWithArgs (
    val route: String,
    val args: List<NamedNavArgument>,
    val routeToBeFormatted: String
) {
    fun withArgs(vararg args: Any) = String.format(routeToBeFormatted, *args)
}