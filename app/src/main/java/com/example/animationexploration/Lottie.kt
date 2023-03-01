package com.example.animationexploration

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Preview
@Composable
fun LottieAnimationExplore() {
    Column {
        Text(
            text = "LottieAnimation Arguments",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            textAlign = TextAlign.Center
        )

        val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sample2))
        var isStop by remember { mutableStateOf(true) }
        Text(text = "stop and restart anim by tap ", modifier = Modifier.padding(top = 24.dp))
        LazyRow(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black, shape = RectangleShape)
                .clickable { isStop = !isStop },
            contentPadding = PaddingValues(12.dp),
        ) {
            item {
                LottieAnimationBox(
                    label = "default ( restartOnPlay : true , reverseOnRepeat: false) ",
                    lottieAnimation = {
//                        var isStop by remember { mutableStateOf(true) }
                        val progress2 by animateLottieCompositionAsState(
                            composition = composition2,
                            isPlaying = isStop,
                            iterations = LottieConstants.IterateForever,
                        )
                        LottieAnimation(
                            modifier = Modifier
                                .clickable { isStop = !isStop },
                            composition = composition2,
                            progress = { progress2 },
                        )
                    }
                )
            }
            item {
                LottieAnimationBox(
                    label = "restartOnPlay : false  reverseOnRepeat: false",
                    lottieAnimation = {
//                        var isStop by remember { mutableStateOf(true) }
                        val progress2 by animateLottieCompositionAsState(
                            composition = composition2,
                            isPlaying = isStop,
                            restartOnPlay = false,
                            iterations = LottieConstants.IterateForever,
                        )
                        LottieAnimation(
                            modifier = Modifier
                                .clickable { isStop = !isStop },
                            composition = composition2,
                            progress = { progress2 },
                        )
                    }
                )
            }
            item {
                LottieAnimationBox(
                    label = "restartOnPlay = true , reverseOnRepeat : true",
                    lottieAnimation = {
//                        var isStop by remember { mutableStateOf(true) }
                        val progress2 by animateLottieCompositionAsState(
                            composition = composition2,
                            isPlaying = isStop,
                            reverseOnRepeat = true,
                            iterations = LottieConstants.IterateForever,
                        )
                        LottieAnimation(
                            modifier = Modifier
                                .clickable { isStop = !isStop },
                            composition = composition2,
                            progress = { progress2 },
                        )
                    }
                )
            }

            item {
                LottieAnimationBox(
                    label = "restartOnPlay : false  & reverseOnRepeat : true",
                    lottieAnimation = {
//                        var isStop by remember { mutableStateOf(true) }
                        val progress2 by animateLottieCompositionAsState(
                            composition = composition2,
                            isPlaying = isStop,
                            restartOnPlay = false,
                            reverseOnRepeat = true,
                            iterations = LottieConstants.IterateForever,
                        )
                        LottieAnimation(
                            modifier = Modifier
                                .clickable { isStop = !isStop },
                            composition = composition2,
                            progress = { progress2 },
                        )
                    }
                )
            }

            item {
                LottieAnimationBox(
                    label = "default + cancellationBehavior : OnIterationFinish",
                    lottieAnimation = {
//                        var isStop by remember { mutableStateOf(true) }
                        val progress2 by animateLottieCompositionAsState(
                            composition = composition2,
                            isPlaying = isStop,
                            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish,
                            iterations = LottieConstants.IterateForever,
                        )
                        LottieAnimation(
                            modifier = Modifier
                                .clickable { isStop = !isStop },
                            composition = composition2,
                            progress = { progress2 },
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun LottieAnimationBox(
    label : String,
    lottieAnimation: @Composable () -> Unit
) {
    Column(modifier = Modifier.size(240.dp)) {
        Text(text = label, modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), textAlign = TextAlign.Center)
        lottieAnimation()
    }
}

//@Composable
//fun LottieSampleLottieAnimationFlags() {
//    val composition by rememberLottieComposition(
//        spec = LottieCompositionSpec.RawRes(R.raw.sample)
//    )
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        isPlaying = true,
//        restartOnPlay = true,
//        reverseOnRepeat = false,
//        clipSpec = null,
//        speed = 1f,
//        iterations = LottieConstants.IterateForever,
//        cancellationBehavior = LottieCancellationBehavior.Immediately,
//        ignoreSystemAnimatorScale = false,
//        useCompositionFrameRate = false
//    )
//    Text(text = "animSpec is set repeat infinite ")
//    LazyRow(
//        modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = RectangleShape),
//        contentPadding = PaddingValues(12.dp),
//    ) {
//        item {
//            LottieAnimationBox(
//                label = "default",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                    )
//                }
//            )
//        }
//        item {
//            LottieAnimationBox(
//                label = "outlineMasksAndMattes : true",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                        outlineMasksAndMattes = true
//                    )
//                }
//            )
//        }
//        item {
//            LottieAnimationBox(
//                label = "applyOpacityToLayers : true",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                        applyOpacityToLayers = true
//                    )
//                }
//            )
//        }
//        item {
//            LottieAnimationBox(
//                label = "enableMergePaths : true",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                        enableMergePaths = true
//                    )
//                }
//            )
//        }
//        item {
//            LottieAnimationBox(
//                label = "maintainOriginalImageBounds : true",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                        maintainOriginalImageBounds = true
//                    )
//                }
//            )
//        }
//        item {
//            LottieAnimationBox(
//                label = "clipToCompositionBounds : true",
//                lottieAnimation = {
//                    LottieAnimation(
//                        composition = composition,
//                        progress = { progress },
//                        clipToCompositionBounds = true
//                    )
//                }
//            )
//        }
//    }
//}

