package com.example.news.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SelectableChipGroup(
    title:String,
    options: List<Int>,
    selected: Int,
    valueChanged: (Int) -> Unit
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { stringRes ->
            ElevatedFilterChip(
                selected = selected == stringRes,
                onClick = { valueChanged(stringRes) },
                label = { Text(text = stringResource(id = stringRes)) })
        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SelectableChipGroup(
    title: String,
    options: List<String>,
    selected: String,
    valueChanged: (String) -> Unit
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { text ->
            ElevatedFilterChip(
                selected = selected == text,
                onClick = { valueChanged(text) },
                label = { Text(text = text) })
        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectChipGroup(
    title: String,
    options: List<Int>,
    selected: List<Int>,
    valueChanged: (Int) -> Unit
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { stringRes ->
            ElevatedFilterChip(
                selected = selected.contains(stringRes),
                onClick = { valueChanged(stringRes) },
                label = { Text(text = stringResource(id = stringRes)) })
        }
    }
}
