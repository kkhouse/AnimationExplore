package com.example.animationexploration

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

/**
 * 表示非表示
 * AnimatedVisibility
 *  animatedContentSize
 *  animateEnterExit
 *  Crossfade
 * https://developer.android.com/jetpack/compose/animation?hl=ja#enter-exit-transition
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityItemsAnimation() {
    var isChanged by remember { mutableStateOf(false) }
    var shown by remember { mutableStateOf(false) }
    LaunchedEffect(isChanged) {
        shown = true
        delay(1000)
        shown = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(3f)
            .clickable { isChanged = !isChanged }
    ) {
        AnimatedVisibility(
            visible = shown,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight }, // initial = 初期位置
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing) // 時間の経過にともなうアニメーション値の変化を指定できる。
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight }, // Target = 終了位置
                animationSpec = tween(durationMillis = 550, easing = FastOutLinearInEasing) // 時間の経過にともなうアニメーション値の変化を指定できる。
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "In out",
                modifier = Modifier
                    .background(Color.Cyan)
                    .animateEnterExit() // AnimatedVisibilityの子のアニメーションも制御可能
            )
        }
    }

    /*
    animateContentSize
    これはコスパ悪い
     */
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
            .animateContentSize() // コンテンツ全体の変更をアニメート
    ) {
        Row(modifier = Modifier.padding(top = 32.dp).animateContentSize()) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "topic",style = MaterialTheme.typography.body1)
        }
        if (isChanged) {
            Text(
                text = "long message",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 12.dp).animateContentSize()
            )
        }
        /*
        Crossfade
         */
        Crossfade(
            modifier = Modifier.animateContentSize(),
            targetState = isChanged
        ) { isChanged ->
            when (isChanged) {
                true -> Text("Page A")
                else -> Text("Page B")
            }
        }
    }
}

/**
 * animate*AsState
 */
@Composable
fun SingleValuesAnimation() {
    var isChanged by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue =  if (isChanged) Color.Cyan else Color.Green )
    val circleSize by animateDpAsState(targetValue = if (isChanged) 48.dp else 80.dp)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clickable { isChanged = !isChanged },
        contentPadding = PaddingValues(32.dp)
    ) {
        item { Circle(color = backgroundColor, size = circleSize) }
    }
}

/**
 * TODO AnimatedContent
 * フェードスルー（フェードインフェードアウト）が超簡単に表現できる
 */

/**
 * animate*AsState
 * Transition
 *  keyframes
 *  infiniteRepeatable
 */
@Composable
fun AnyValuesAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes { // ms単位の複数の時点で進行中のアニメーション値を変更できる
                durationMillis = 1000
                0.7f at 500 // 500msの時点で0.7fのalpha
                0.9f at 800 // 800msの時点で0.9fのalpha
            },
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Circle(color = Color.Red.copy(alpha = alpha))
    }
}

/**
 * AnimatedImageVector
 */
@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimateVector() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.animatedvector)
    var atEnd by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = "Timer",
        modifier = Modifier.clickable {
            atEnd = !atEnd
        },
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun SingleValuesAnimationPreview() {
    SingleValuesAnimation()
}
@Preview
@Composable
fun AnyValuesAnimationPreview() {
    AnyValuesAnimation()
}
@Preview
@Composable
fun VisibilityItemsAnimationPreview() {
    VisibilityItemsAnimation()
}
@Preview
@Composable
fun AnimateVectorPreview() {
    AnimateVector()
}