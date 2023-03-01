package com.example.animationexploration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
    color: Color = Color.Red,
    size: Dp = 48.dp,
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color = color)
    )
}

@Composable
fun Toggle(text: String,
           value: Float,
           range: ClosedFloatingPointRange<Float>,
           onValueChange: (Float) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(30.dp)
    ) {
        Text(text, modifier = Modifier.width(100.dp))
        Slider(
            value = value,
            onValueChange,
            valueRange = range
        )
    }
}

enum class BoxState {
    Collapsed,
    Expanded
}