package com.example.model.task

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


sealed class LineStyle {
    class Normal(
        val width: Dp = 6.dp,
        val color: Color = Color.Gray
    ) : LineStyle()

    class Dashed(
        val width: Dp = 6.dp,
        val color: Color = Color.Gray,
        val dashLength: Dp = 0.dp,
        val dashGap: Dp = 0.dp
    ) : LineStyle()
}

internal fun LineStyle.width(): Dp {
    return when (this) {
        is LineStyle.Normal -> this.width
        is LineStyle.Dashed -> this.width
    }
}

internal fun LineStyle.color(): Color {
    return when (this) {
        is LineStyle.Normal -> this.color
        is LineStyle.Dashed -> this.color
    }
}