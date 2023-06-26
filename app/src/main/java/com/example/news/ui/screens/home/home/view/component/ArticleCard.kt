package com.example.news.ui.screens.home.home.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ArticleCard(
    modifier: Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {

        Box {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = article.thumbnailUrl,
                contentDescription = null,
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
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = .4f))
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Row {
                    //source
                    Text(
                        text = article.source,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    //time
                    Text(
                        text = "● ${article.publishedFrom}",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                //title
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
