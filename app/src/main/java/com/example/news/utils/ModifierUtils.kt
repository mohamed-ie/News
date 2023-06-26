package com.example.news.utils

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun Modifier.shimmer(
    enabled: Boolean = true,
    shape: Shape = RectangleShape
): Modifier = composed {
    if (enabled) {

        var size by remember { mutableStateOf(IntSize.Zero) }

        val transition = rememberInfiniteTransition()

        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(animation = tween(1500))
        )

        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Gray.copy(alpha = .5f),
                    Color.Gray,
                    Color.Gray.copy(alpha = .5f)
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            ),
            shape = shape
        ).onGloballyPositioned {
            size = it.size
        }
    } else {
        background(
            brush = Brush.linearGradient(
                colors = listOf(Color.Transparent, Color.Transparent),
                start = Offset.Zero,
                end = Offset.Zero
            )
        )
    }
}


fun Modifier.loading(
    enabled: Boolean = true,
    startSize: Float = 10f,
    endSize: Float = 50f,
    startColor: @Composable () -> Color = { MaterialTheme.colorScheme.primary.copy(alpha = .6f) },
    endColor: @Composable () -> Color = { MaterialTheme.colorScheme.primary.copy(alpha = .2f) }
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateFloat(
        initialValue = startSize,
        targetValue = endSize,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val color by infiniteTransition.animateColor(
        initialValue = startColor(),
        targetValue = endColor(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    drawWithContent {
        if (enabled)
            drawCircle(color = color, radius = size)
        else
            drawContent()
    }

}


