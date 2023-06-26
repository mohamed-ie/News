package com.example.news.ui.screens.details.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.SubcomposeAsyncImage
import com.example.news.R
import com.example.news.utils.shimmer
import com.example.news.ui.theme.NewsTheme


@Composable
fun ArticleContent(state: ArticleState, back: () -> Unit, onEvent: (ArticleEvent) -> Unit) {
    val article = state.article
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight.div(2f))
        ) {
            //topbar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .zIndex(3f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //back
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .2f))
                        .clickable(role = Role.Button, onClick = back)
                        .padding(8.dp),
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                //bookmark
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .2f))
                        .clickable(role = Role.Button, onClick = { onEvent(ArticleEvent.Bookmark) })
                        .padding(8.dp),
                    imageVector = if (article.isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))
                //more
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .2f))
                            .clickable(
                                role = Role.Button,
                                onClick = { onEvent(ArticleEvent.ToggleMoreMenuExpandState) })
                            .padding(8.dp),
                        imageVector = Icons.Rounded.MoreHoriz,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DropdownMenu(
                        expanded = state.isMoreMenuExpanded,
                        onDismissRequest = { onEvent(ArticleEvent.ToggleMoreMenuExpandState) }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.read_full_article))
                            },
                            onClick = { uriHandler.openUri(article.url) },
                        )
                    }
                }
            }

            SubcomposeAsyncImage(
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth(),
                model = article.thumbnailUrl,
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
                },
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
                    .background(Color.Gray.copy(alpha = .4f))
            )

            Column(
                modifier = Modifier
                    .zIndex(1f)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 32.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {

                //title
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                //time
                Text(
                    text = "● ${article.publishedFrom}",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .offset(y = (-16).dp)
                .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            //source
            Text(
                text = article.source,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
            //description
            article.description?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(16.dp))
            //content
            article.content?.let { Text(text = it) }
        }
    }
}

@Preview
@Composable
fun PreviewArticleContent() {
    NewsTheme {
        ArticleContent(state = ArticleState(), {}) {}
    }
}