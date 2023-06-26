package com.example.news.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.ui.theme.NewsTheme
import com.example.news.utils.loading

@Composable
fun LoadingScreen() {
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.loading())
    }
}

@Preview
@Composable
fun PreviewLoadingScreen() {
    NewsTheme {
        LoadingScreen()
    }
}