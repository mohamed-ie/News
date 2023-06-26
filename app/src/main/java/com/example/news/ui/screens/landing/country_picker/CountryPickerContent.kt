package com.example.news.ui.screens.landing.country_picker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.example.news.R
import com.example.news.ui.common.component.country_card.CountryCard
import com.example.news.ui.navigation.graph.Graph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountryPickerContent(
    state: CountryPickerState,
    onEvent: (CountryPickerEvent) -> Unit,
    navigateTo: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    SearchBar(
        modifier = Modifier.fillMaxSize(),
        query = state.query,
        onQueryChange = { onEvent(CountryPickerEvent.SearchQueryChanged(it)) },
        onSearch = { keyboardController?.hide() },
        active = true,
        onActiveChange = {},
        leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = null) },
        trailingIcon = {
            ClearButton(visible = state.isClearButtonVisible, onClick = {
                onEvent(CountryPickerEvent.SearchQueryChanged(""))
            })
        },
        placeholder = { Text(text = stringResource(id = R.string.search)) },
    ) {
        Scaffold(
            floatingActionButton = {
                FAB(
                    visible = state.isDoneButtonVisible,
                    onClick = {
                        onEvent(CountryPickerEvent.SaveCountry)
                        navigateTo(Graph.HOME)
                    })
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            LazyColumn(Modifier.padding(innerPadding)) {
                items(state.countries) { country ->
                    CountryCard(
                        country = country,
                        selected = country.code == state.selectedCode,
                        onClick = { onEvent(CountryPickerEvent.CountrySelected(country.code)) }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FAB(visible: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(visible = visible, enter = scaleIn(), exit = scaleOut()) {
        FloatingActionButton(onClick = onClick) {
            Icon(imageVector = Icons.Rounded.Done, contentDescription = null)
        }
    }
}

@Composable
private fun ClearButton(visible: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(visible = visible) {
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
        }
    }
}