package com.kt.basickit.bottomsheet

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.kt.basickit.bottomsheet.component.DragButton

// TODO
//  1. height option full, half, custom..
//  2. height auto 일 때 drag

@Composable
fun BottomSheetCompose(
    type: BottomSheetType = DefaultBottomSheet,
    options: BottomSheetOptions = BottomSheetOptions(),
    scrollState: ScrollState = rememberScrollState(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    // 켜고 끌때 Transition Animation
    val animationState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    // willClose? animateBeforeClose? ...?
    fun prepareToClose() {
        animationState.targetState = false
    }

    var offsetY by remember {
        mutableStateOf(
            when (type) {
                is FullBottomSheet -> {
                    screenHeight.value
                }

                is HalfBottomSheet -> {
                    screenHeight.value / 2
                }

                is CustomBottomSheet -> {
                    type.height
                }

                is DefaultBottomSheet -> {
                    40f
                }
            },
        )
    }

    fun checkCloseState(): Boolean {
        return if (type is FullBottomSheet && offsetY.dp < (screenHeight / 2)) {
            true
        } else if (type is HalfBottomSheet && offsetY.dp < (screenHeight / 4)) {
            true
        } else {
            offsetY < 50f
        }
    }

    fun handleDrag(delta: Float) {
        offsetY -= (delta / 2) // 감도 조절..

        if (checkCloseState()) {
            prepareToClose()
        }
    }

    if (!animationState.targetState && !animationState.currentState) {
        onDismissRequest()
    }

    Popup(
        alignment = Alignment.BottomCenter,
        onDismissRequest = { },
        properties = PopupProperties(focusable = true),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .noRippleClickable {
                    prepareToClose()
                }
                .background(color = options.dimmingColor)
                .imePadding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AnimatedVisibility(
                modifier = Modifier,
                visibleState = animationState,
                enter = slideInVertically(
                    animationSpec = tween(durationMillis = 170),
                ) {
                    with(density) { offsetY.dp.roundToPx() }
                },
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = 150),
                ) {
                    with(density) { offsetY.dp.roundToPx() }
                },
            ) {
                when (type) {
                    is DefaultBottomSheet ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(
                                    options.contentBackgroundColor,
                                    shape = RoundedCornerShape(
                                        options.borderRadius,
                                        options.borderRadius,
                                        0,
                                        0,
                                    ),
                                )
                                .padding(options.contentPadding)
                                .fillMaxWidth(options.contentWidth)
                                .verticalScroll(scrollState)
                                .noRippleClickable {}
                                .zIndex(1.0f),
                        ) {
                            content()
                        }

                    else ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(
                                    options.contentBackgroundColor,
                                    shape = RoundedCornerShape(
                                        options.borderRadius,
                                        options.borderRadius,
                                        0,
                                        0,
                                    ),
                                )
                                .padding(options.contentPadding)
                                .fillMaxWidth(options.contentWidth)
                                .verticalScroll(scrollState)
                                .noRippleClickable {}
                                .height(offsetY.dp)
                                .zIndex(1.0f),
                        ) {
                            if (options.isDraggable) {
                                DragButton(
                                    handleDrag = { delta -> handleDrag(delta) },
                                )
                            }
                            content()
                        }
                }
            }
        }
    }
}
