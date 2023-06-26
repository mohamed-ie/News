package com.example.news.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.news.utils.ScreenState

open class BaseViewModel : ViewModel() {
    var screenState by mutableStateOf<ScreenState>(ScreenState.Loading)
        private set

    protected fun toStableScreenState() {
        screenState = ScreenState.Stable
    }

    protected fun toErrorScreenState() {
        screenState = ScreenState.Error
    }

    protected fun toLoadingScreenState() {
        screenState = ScreenState.Loading
    }

}