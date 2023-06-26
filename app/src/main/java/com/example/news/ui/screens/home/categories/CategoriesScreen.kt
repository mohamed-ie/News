package com.example.news.ui.screens.home.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Biotech
import androidx.compose.material.icons.rounded.Healing
import androidx.compose.material.icons.rounded.Interests
import androidx.compose.material.icons.rounded.QueryStats
import androidx.compose.material.icons.rounded.Science
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.SportsSoccer
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.ui.navigation.screen.DetailsScreen

@Composable
fun CategoriesScreen(navigateTo: (String) -> Unit) {
    val categories = listOf(
        Pair(R.string.business, Icons.Rounded.QueryStats),
        Pair(R.string.entertainment, Icons.Rounded.SportsEsports),
        Pair(R.string.general, Icons.Rounded.Interests),
        Pair(R.string.health, Icons.Rounded.Healing),
        Pair(R.string.science, Icons.Rounded.Science),
        Pair(R.string.sports, Icons.Rounded.SportsSoccer),
        Pair(R.string.technology, Icons.Rounded.Biotech)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categories) { category ->
            val text = stringResource(id = category.first)
            CategoryItem(
                text = text,
                category.second,
                onClick = { navigateTo(DetailsScreen.AllArticles.withArgs(text)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryItem(
    text: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
            Icon(imageVector = imageVector, contentDescription = null)
        }
    }
}
