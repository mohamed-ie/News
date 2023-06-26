package com.example.news.ui.common.component.min_article_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.news.R
import com.example.news.data.local.db.entity.Article
import com.example.news.utils.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MinArticleCard(
    article: Article,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .height(125.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                model = article.thumbnailUrl,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .shimmer()
                    )
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.news_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                },
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                //title
                article.title.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //source
                        Text(
                            text = article.source,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray
                        )

                    Spacer(modifier = Modifier.width(16.dp))
                    //time
                    Text(
                        text = "● ${article.publishedFrom}",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}