package com.kt.basickit.bottomsheet

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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.kt.basickit.bottomsheet.component.DragButton

// TODO
//  1. height option full, half, custom..
//  3. TextField 확인(API 30)

@Composable
fun BottomSheetCompose(
    options: BottomSheetOptions = BottomSheetOptions(),
    scrollState: ScrollState = rememberScrollState(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current

    // 켜고 끌때 Transition Animation
    val animationState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    if (!animationState.targetState && !animationState.currentState) {
        onDismissRequest()
    }

    var offsetY by remember { mutableFloatStateOf(options.contentHeight ?: density.density) }
    fun animateBeforeClose() {
        animationState.targetState = false
    }

    fun onChangeOffset(delta: Float) {
        offsetY += (delta / 3) // 감도 조절..

        if (offsetY < 50f) {
            animateBeforeClose()
        }
    }

    var contentsModifier = Modifier
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
        .zIndex(1.0f)

    if (options.contentHeight != null) {
        contentsModifier = contentsModifier.height(options.contentHeight.dp)
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
                    animateBeforeClose()
                }
                .background(color = options.dimmingColor)
                .imePadding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AnimatedVisibility(
                visibleState = animationState,
                enter = slideInVertically(
                    animationSpec = tween(durationMillis = 200),
                ) {
                    with(density) { 40.dp.roundToPx() }
                },
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = 200),
                ) {
                    with(density) { (offsetY).dp.roundToPx() }
                },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = contentsModifier,
                ) {
                    if (options.isDraggable) {
                        DragButton(
                            onChangeOffset = { delta -> onChangeOffset(delta) },
                        )
                    }
                    content()
                }
            }
        }
    }
}
