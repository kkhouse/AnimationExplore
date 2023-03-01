package com.example.animationexploration

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun GraphicsLayerSettingPreview() {
    GraphicsLayerSetting()
}

/**
 * ここから拝借
 * https://github.com/elye/demo_android_jetpack_compose_graphicslayer_modifier/blob/main/app/src/main/java/com/example/graphiclayermodifier/GraphicsLayerSetting.kt
 */
@Composable
fun GraphicsLayerSetting() {

    var scaleX by remember { mutableStateOf(1f) }
    var scaleY by remember { mutableStateOf(1f) }
    var alpha by remember { mutableStateOf(1f) }
    var originX by remember { mutableStateOf(0.5f) }
    var originY by remember { mutableStateOf(0.5f) }
    var translationX by remember { mutableStateOf(0f) }
    var translationY by remember { mutableStateOf(0f) }
    var cameraDistance by remember { mutableStateOf(8f) }
    var shadowElevation by remember { mutableStateOf(0f) }
    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    var rotationZ by remember { mutableStateOf(0f) }
    var blur by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val roundedDegree = RoundedCornerShape(8.dp)
        CircleShape
        Box(
            Modifier
                .graphicsLayer(
                    alpha = alpha,
                    translationX = translationX,
                    translationY = translationY,
                    shadowElevation = shadowElevation,
                    scaleX = scaleX,
                    scaleY = scaleY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance,
                    transformOrigin = TransformOrigin(originX, originY),
                    shape = roundedDegree,
                    clip = true,
                    renderEffect = BlurEffect(blur, blur)
                )
                .background(Color(97, 140, 85))
                .size(100.dp, 100.dp)
                .border(5.dp, Color.Red, shape = roundedDegree)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                "NE",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
        }

        Toggle("Alpha", alpha, 0f..1f) { alpha = it }
        Toggle("TranslationX", translationX, -100f..100f) { translationX = it }
        Toggle("TranslationY", translationY, -100f..100f) { translationY = it }
        Toggle("Shadow", shadowElevation, 0f..500f) { shadowElevation = it }
        Toggle("ScaleX", scaleX, 0.5f..2f) { scaleX = it }
        Toggle("ScaleY", scaleY, 0.5f..2f) { scaleY = it }
        Toggle("RotateX", rotationX, -360f..360f) { rotationX = it }
        Toggle("RotateY", rotationY, -360f..360f) { rotationY = it }
        Toggle("RotateZ", rotationZ, -360f..360f) { rotationZ = it }
        Toggle("OriginX", originX, 0f..1f) { originX = it }
        Toggle("OriginY", originY, 0f..1f) { originY = it }
        Toggle("CameraDist", cameraDistance, 3f..50f) { cameraDistance = it }
        Toggle("Blur", blur, 1f..100f) { blur = it }
    }
}