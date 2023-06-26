package com.example.news.ui.screens.home.settings.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.common.ArticlesRetrievalCount
import com.example.news.common.RefreshInterval
import com.example.news.ui.screens.home.settings.SettingsViewModel
import com.example.news.ui.screens.home.settings.view.component.CountriesDialog
import com.example.news.ui.screens.home.settings.view.component.SettingsDialog

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, navigateTo: (String) -> Unit) {
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(start = 32.dp, top = 24.dp, bottom = 32.dp),
            text = stringResource(id = R.string.Settings),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        SettingItem(
            title = stringResource(id = R.string.country),
            value = state.country,
            onClick = { viewModel.onEvent(SettingsEvent.ShowCountriesDialog) })

        SettingItem(
            title = stringResource(id = R.string.retrieve_articles_by),
            value = stringResource(id = state.retrieveCount),
            onClick = { viewModel.onEvent(SettingsEvent.ShowRetrievalCountDialog) })

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.auto_refresh),
                style = MaterialTheme.typography.titleLarge
            )

            Switch(
                checked = state.autoRefresh,
                onCheckedChange = { viewModel.onEvent(SettingsEvent.ToggleAutoRefresh) },
            )
        }


        AnimatedVisibility(
            visible = state.autoRefresh,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top)
        ) {
            SettingItem(
                title = stringResource(id = R.string.refresh_interval),
                value = stringResource(id = state.refreshInterval),
                onClick = { viewModel.onEvent(SettingsEvent.ShowRefreshIntervalDialog) })
        }
    }

    AnimatedVisibility(visible = state.isCountriesDialogVisible) {
        CountriesDialog(
            query = state.query,
            countries = state.countries,
            onEvent = viewModel::onEvent
        )
    }

    AnimatedVisibility(visible = state.isRefreshIntervalDialogVisible) {
        SettingsDialog(
            radioOptions = RefreshInterval.OPTIONS,
            selectedOption = state.refreshInterval,
            onOptionSelected = { viewModel.onEvent(SettingsEvent.RefreshIntervalChanged(it)) },
            onCancel = { viewModel.onEvent(SettingsEvent.HideRefreshIntervalDialog) }
        )
    }

    AnimatedVisibility(visible = state.isRetrieveCountDialogVisible) {
        SettingsDialog(
            radioOptions = ArticlesRetrievalCount.OPTIONS,
            selectedOption = state.retrieveCount,
            onOptionSelected = { viewModel.onEvent(SettingsEvent.RetrievalCountChanged(it)) },
            onCancel = { viewModel.onEvent(SettingsEvent.HideRetrievalCountDialog) }
        )
    }
}

@Composable
private fun SettingItem(title: String, value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = .4f
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(role = Role.Button) { onClick() }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
