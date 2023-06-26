package com.example.news.utils

sealed interface ScreenState {
    object Stable : ScreenState
    object Loading : ScreenState
    object Error : ScreenState
}