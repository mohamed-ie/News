package com.example.news.ui.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.news.ui.navigation.screen.LandingScreen
import com.example.news.ui.screens.landing.country_picker.CountryPickerScreen
import com.example.news.ui.screens.landing.splash.SplashScreen


fun NavGraphBuilder.landingNavGraph(navController: NavHostController) {
    navigation(route = Graph.LANDING, startDestination = LandingScreen.Splash.route) {
        composable(route = LandingScreen.Splash.route) {
            SplashScreen(viewModel = hiltViewModel(), navigateTo = {
                navController.navigate(it) {
                    popUpTo(it)
                }
            })
        }
        composable(route = LandingScreen.CountryPicker.route) {
            CountryPickerScreen(viewModel = hiltViewModel(), navigateTo = navController::navigate)
        }

    }
}
