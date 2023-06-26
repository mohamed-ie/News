package com.example.news.ui.screens.home.settings.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.news.R


@Composable
fun SettingsDialog(
    radioOptions: List<Int>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        ElevatedCard(
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                    top = 24.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { textRes ->
                        SettingsDialogOption(textRes, selectedOption, onOptionSelected)
                    }

                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCancel
                    ) { Text(text = stringResource(id = R.string.cancel)) }
                }
            }
        }
    }
}

@Composable
private fun SettingsDialogOption(
    textRes: Int,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(MaterialTheme.shapes.medium)
            .selectable(
                selected = (textRes == selectedOption),
                onClick = { onOptionSelected(textRes) },
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
        )

        RadioButton(
            selected = (textRes == selectedOption),
            onClick = null
        )
    }
}
