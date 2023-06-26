package com.example.news.ui.navigation.screen


sealed class LandingScreen(val route: String) {
    object Splash : LandingScreen(route = "splash")
    object CountryPicker : LandingScreen(route = "country_picker")
}