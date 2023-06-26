package com.example.news.ui.screens.home.settings.view.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.news.R
import com.example.news.domain.model.Country
import com.example.news.ui.screens.home.settings.view.SettingsEvent

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun CountriesDialog(
    query: String,
    countries: List<Country>,
    onEvent: (SettingsEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Dialog(onDismissRequest = { onEvent(SettingsEvent.HideCountriesDialog) }) {
        DockedSearchBar(
            query = query,
            placeholder = { Text(text = stringResource(id = R.string.search_country)) },
            onQueryChange = { onEvent(SettingsEvent.SearchQueryChanged(it)) },
            onSearch = { keyboardController?.hide() },
            active = true,
            onActiveChange = {}) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.padding(bottom = 64.dp)) {
                    items(countries) { country ->
                        MinCountryCard(
                            country = country,
                            onClick = { onEvent(SettingsEvent.CountryChanged(country.code)) },
                        )
                    }
                }
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.BottomCenter),
                    onClick = { onEvent(SettingsEvent.HideCountriesDialog) }
                ) { Text(text = stringResource(id = R.string.cancel)) }
            }
        }
    }
}