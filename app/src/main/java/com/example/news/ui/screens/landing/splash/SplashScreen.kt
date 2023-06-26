package com.example.news.ui.screens.landing.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.news.R
import com.example.news.ui.navigation.graph.Graph
import com.example.news.ui.navigation.screen.LandingScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigateTo: (String) -> Unit
) {
    Box {
        LaunchedEffect(true) {
            delay(1000L)
            if (viewModel.isFirstTime.first())
                navigateTo(LandingScreen.CountryPicker.route)
            else
                navigateTo(Graph.HOME)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}