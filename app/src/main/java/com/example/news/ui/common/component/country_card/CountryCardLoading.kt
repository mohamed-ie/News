package com.example.news.ui.common.component.country_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.news.utils.shimmer

@Composable
fun CountryCardLoading() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .shimmer(shape = MaterialTheme.shapes.small),
                    text = "🇨🇴",
                    color = Color.Transparent,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .shimmer(shape = MaterialTheme.shapes.small),
                    text = "",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .shimmer(shape = MaterialTheme.shapes.small),
                    text = "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
