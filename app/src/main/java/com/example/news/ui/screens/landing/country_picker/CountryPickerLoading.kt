package com.example.news.ui.screens.landing.country_picker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.news.ui.common.component.country_card.CountryCardLoading
import com.example.news.utils.shimmer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CountryPickerLoading() {


    LazyColumn {
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SearchBarDefaults.InputFieldHeight)
                    .shimmer()
            )
        }

        items(8) {
            CountryCardLoading()
        }
    }
}