package com.example.news.ui.screens.details.article.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.news.utils.shimmer


@Composable
internal fun ArticleLoading(back: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), true)
    ) {
        val (thumbnail, title, time, topBar, content) = createRefs()
        //thumbnail
        Box(
            modifier = Modifier
                .constrainAs(thumbnail) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1")
                }
                .shimmer()
        )

        //topbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .background(color = Color.Gray.copy(alpha = .5f), shape = CircleShape),
                onClick = back
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        }

        //title
        Box(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(title) {
                    bottom.linkTo(time.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .shimmer()
        )

        //time
        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(.3f)
                .padding(16.dp)
                .constrainAs(time) {
                    bottom.linkTo(thumbnail.bottom, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(time.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            //source
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxSize(.6f)
                    .clip(shape = MaterialTheme.shapes.small)
                    .shimmer()
            )

            Spacer(modifier = Modifier.height(16.dp))
            //description
            repeat(3) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.small)
                        .height(24.dp)
                        .shimmer()

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            //content
            repeat(8) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .height(16.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .shimmer()
                )
            }
        }
    }
}
