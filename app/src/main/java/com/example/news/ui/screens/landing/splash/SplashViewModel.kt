package com.example.news.ui.screens.landing.splash

import androidx.lifecycle.ViewModel
import com.example.news.domain.use_case.sources.IsFirstTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    isFirstTimeUseCase: IsFirstTimeUseCase,
) : ViewModel() {
    val isFirstTime = isFirstTimeUseCase()
}