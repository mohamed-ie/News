package com.example.news.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.news.data.local.db.entity.Article
import com.example.news.domain.use_case.IsInternetConnectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val isInternetConnectedUseCase: IsInternetConnectedUseCase
) : ViewModel() {
    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()

    val isInternetConnected = isInternetConnectedUseCase()

    fun setArticle(article: Article) {
        _article.update { article }
    }
}