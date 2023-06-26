package com.example.news.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
internal inline fun <reified V : ViewModel> NavBackStackEntry.getParentViewModel(
    navController: NavHostController,
    parentRoute: String
): V {
    val parentEntry = remember(this) {
        navController.getBackStackEntry(parentRoute)
    }
    return hiltViewModel(parentEntry)
}