package com.kt.basickit.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
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
    val contentsModifier = if (options.contentHeight != null) {
        Modifier
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
            .height(options.contentHeight.dp)
            .verticalScroll(scrollState)
            .noRippleClickable {}
            .zIndex(1.0f)
    } else {
        Modifier
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
    }
    val animationState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    var offsetY by remember { mutableFloatStateOf(options.contentHeight ?: density.density) }
    if (!animationState.targetState && !animationState.currentState) {
        onDismissRequest()
    }

    fun animateBeforeClose() {
        animationState.targetState = false
    }

    fun onChangeOffset(delta: Float) {
        offsetY -= (delta / 3) // 감도 조절..

        if (offsetY < 50f) {
            animateBeforeClose()
        }
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

/**
 * Bottom sheet options
 *
 * @property dimmingColor: BottomSheet 배경 Dimming 색깔
 * @property borderRadius: BottomSheet 좌, 우측 상단 BorderRadius 값
 * @property contentBackgroundColor
 * @property contentPadding: 내부 Content 의 패딩
 * @property contentWidth: 가로 크기의 비율. ex) 0.7f 일 경우 가로의 70%크기의 BottomSheet 가 만들어 짐.
 * @constructor 모든 요소는 기본 값이 있어서 따로 지정 하지 않으면 기본 BottomSheet 디자인이 적용됨.
 */
data class BottomSheetOptions(
    val isDraggable: Boolean = true,
    val dimmingColor: Color = Color.Gray.copy(alpha = 0.2f),
    val borderRadius: Int = 10,
    val contentBackgroundColor: Color = Color.White,
    val contentPadding: PaddingValues = PaddingValues(20.dp, 10.dp),
    val contentWidth: Float = 1f, // 쓸까..?
    val contentHeight: Float? = null,
)

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        onClick()
    }
}
