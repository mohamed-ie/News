@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.news.ui.screens.search.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.news.R
import com.example.news.ui.common.component.MultiSelectChipGroup
import com.example.news.ui.common.component.SelectableChipGroup

private val searchInOption = listOf(
    R.string.title,
    R.string.description,
    R.string.content,
)
private val languages = listOf(
    "all",
    "ar",
    "en",
    "de",
    "es",
    "fr",
    "he",
    "it",
    "nl",
    "no",
    "pt",
    "ru",
    "sv",
    "ud",
    "zh"
)

private val sortOptions = listOf(
    R.string.relevancy,
    R.string.popularity,
    R.string.published_at
)

@Composable
fun ManageSearchDialog(
    state: ManageSearchState,
    searchInOptionChanged: (Int) -> Unit,
    openDateRangePicker: () -> Unit,
    languageChanged: (String) -> Unit,
    sortOptionChanged: (Int) -> Unit,
    onDismiss: () -> Unit,
    apply: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                //search in
                MultiSelectChipGroup(
                    title = stringResource(id = R.string.search_in),
                    options = searchInOption,
                    selected = state.selectedSearchInOptions,
                    valueChanged = { searchInOptionChanged(it) }
                )

                //from to date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //from
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(id = R.string.from),
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(onClick = openDateRangePicker) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = state.fromDate,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    //to
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(id = R.string.to),
                            fontWeight = FontWeight.Bold
                        )

                        TextButton(onClick = openDateRangePicker) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = state.toDate,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                //languages
                SelectableChipGroup(
                    title = stringResource(id = R.string.language),
                    options = languages,
                    selected = state.selectedLanguage,
                    valueChanged = { languageChanged(it) }
                )

                //sort by
                SelectableChipGroup(
                    title = stringResource(id = R.string.sort_by),
                    options = sortOptions,
                    selected = state.selectedSortOption,
                    valueChanged = { sortOptionChanged(it) }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //cancel
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    //apply
                    Button(onClick = apply) {
                        Text(text = stringResource(id = R.string.apply))
                    }
                }
            }
        }
    }
}


