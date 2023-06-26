package com.example.news.ui.screens.landing.country_picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.news.utils.ScreenState

@Composable
fun CountryPickerScreen(viewModel: CountryPickerViewModel,navigateTo:(String)->Unit) {
    val state by viewModel.state.collectAsState()
    when(viewModel.screenState){
        is ScreenState.Error -> {}
        ScreenState.Loading -> CountryPickerLoading()
        ScreenState.Stable -> CountryPickerContent(state = state, onEvent = viewModel::onEvent, navigateTo = navigateTo )
    }
}