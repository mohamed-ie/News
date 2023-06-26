package com.example.news.ui.screens.search.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ManageSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.ui.navigation.screen.DetailsScreen
import com.example.news.ui.screens.home.SharedViewModel
import com.example.news.ui.common.component.min_article_card.MinArticleCard
import com.example.news.ui.screens.details.all_articles.view.AllArticlesEvent
import com.example.news.ui.screens.search.SearchViewModel
import com.example.news.ui.screens.search.view.component.DateRangePickerDialog
import com.example.news.ui.screens.search.view.component.ManageSearchDialog
import com.example.news.ui.screens.search.view.component.ManageSearchEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    sharedViewModel: SharedViewModel,
    navigateTo: (String) -> Unit,
    back: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()
    val manageSearchDialogState by viewModel.manageSearchDialogState.collectAsState()
    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            query = state.searchQuery,
            onQueryChange = { viewModel.onEvent(SearchEvent.SearchQueryChanged(it)) },
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvent.Search)
            },
            active = true,
            onActiveChange = {},
            placeholder = { Text(stringResource(id = R.string.search)) },
            leadingIcon = {
                IconButton(modifier = Modifier.padding(end = 8.dp), onClick = back) {
                    Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
            },
            trailingIcon = {
                Row {
                    AnimatedVisibility(visible = state.isSearchVisible) {
                        IconButton(onClick = { viewModel.onEvent(SearchEvent.Search) }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    IconButton(onClick = { viewModel.onEvent(SearchEvent.ShowManageSearchDialog) }) {
                        Icon(Icons.Default.ManageSearch, contentDescription = null)
                    }
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(state.articlesData.articles) {
                    MinArticleCard(article = it, onClick = {
                        sharedViewModel.setArticle(it)
                        navigateTo(DetailsScreen.ARTICLE_DETAILS)
                    })
                }

                item {
                    AnimatedVisibility(visible = state.isLoadMoreVisible) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = { viewModel.onEvent(SearchEvent.LoadMore) }
                        ) {
                            Text(
                                text = stringResource(id = R.string.load_more),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(visible = state.isManageSearchDialogVisible) {
        ManageSearchDialog(
            state = manageSearchDialogState,
            searchInOptionChanged = {
                viewModel.onManageSearchDialogEvent(ManageSearchEvent.SearchInOptionChanged(it))
            },
            languageChanged = {
                viewModel.onManageSearchDialogEvent(ManageSearchEvent.LanguageChanged(it))
            },
            sortOptionChanged = {
                viewModel.onManageSearchDialogEvent(ManageSearchEvent.SortOptionChanged(it))
            },
            apply = { viewModel.onEvent(SearchEvent.ApplySearchOption) },
            onDismiss = { viewModel.onEvent(SearchEvent.DismissManageSearchDialog) },
            openDateRangePicker = { viewModel.onEvent(SearchEvent.ShowDateRangePickerDialog) }
        )
    }

    AnimatedVisibility(visible = state.isDateRangePickerDialogVisible) {
        DateRangePickerDialog(
            selectedRange = LongRange(
                manageSearchDialogState.startMills,
                manageSearchDialogState.endMills
            ),
            onSave = { startMills, endMills ->
                viewModel.onEvent(SearchEvent.DateRangeChanged(startMills, endMills))
            },
            onDismiss = { viewModel.onEvent(SearchEvent.HideDateRangePickerDialog) }
        )
    }
}
