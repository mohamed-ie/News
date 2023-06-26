package com.example.news.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.news.R
import com.example.news.ui.theme.NewsTheme

@Composable
fun ErrorScreen(
    title: String,
    message: String,
    confirmText: String = stringResource(id = R.string.ok),
    dismissText: String = stringResource(id = R.string.cancel),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit = onDismiss
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Dialog(onDismissRequest = onDismissRequest) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        style =MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = message)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = dismissText,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Button(onClick = onConfirm) {
                            Text(text = confirmText)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@ExperimentalMaterial3Api
@Composable
fun Preview() {
    NewsTheme {
        ErrorScreen("Error",
            "Error while fetching news sources",
            onConfirm = {},
            onDismiss = {})
    }
}