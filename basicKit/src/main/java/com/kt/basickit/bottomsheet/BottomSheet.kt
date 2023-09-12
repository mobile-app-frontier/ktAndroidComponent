package com.kt.basickit.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import com.kt.basickit.bottomsheet.component.DefaultDragButton

/**
 * Bottom sheet
 *
 * @param options 디자인, 기능적 옵션
 * @param onDismissRequest BottomSheet 닫을 때 실행됨.
 * @param content BottomSheet 내부 UI 컨텐츠
 */
@Composable
fun BottomSheet(
    options: BottomSheetOptions = BottomSheetOptions(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val fullPadding = 40f // type FullBottomSheet 일 때 위에 패딩 값
    val minimumSize = 50f // BottomSheet 최소 높이
    val animationDuration = 200

    // 켜고 끌때 Transition Animation
    val animationState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    // BottomSheet 닫히기 전 Animation
    fun closeSheetWithAnimation() {
        animationState.targetState = false
    }

    val bottomSheetFullSize = screenHeight.value - fullPadding

    val maximumHeight = when (options.type) {
        is BottomSheetType.Full -> {
            bottomSheetFullSize
        }

        is BottomSheetType.Half -> {
            screenHeight.value / 2
        }

        is BottomSheetType.Custom -> {
            options.type.maxHeight ?: bottomSheetFullSize
        }

        is BottomSheetType.Default -> { // 의미 없음 안씀 ㅎㅎ
            0f
        }
    }

    var offsetY by remember {
        mutableStateOf(
            if (options.type is BottomSheetType.Custom) {
                options.type.minHeight
            } else {
                maximumHeight
            },
        )
    }

    fun isCloseHeightReached(): Boolean {
        return if (options.type is BottomSheetType.Full && offsetY.dp < (screenHeight / 2)) {
            true
        } else {
            offsetY < minimumSize
        }
    }

    // Drag 중
    fun onDrag(delta: Float) {
        val executedDelta = (delta / 3) // 감도 조절..

        // 이상 행동 막음
        if ((offsetY - executedDelta) > maximumHeight || offsetY - executedDelta < 0) {
            return
        }
        offsetY -= executedDelta
    }

    // Drag 끝나는 시점에 BottomSheet을 닫아야 할지 판단.
    fun dragEnd() {
        if (isCloseHeightReached()) {
            closeSheetWithAnimation()
        }
    }

    if (!animationState.targetState && !animationState.currentState) {
        onDismissRequest()
    }

    Popup(
        alignment = Alignment.TopStart,
        onDismissRequest = {
            closeSheetWithAnimation()
        },
        properties = PopupProperties(focusable = true),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .noRippleClickable {
                    closeSheetWithAnimation()
                }
                .background(color = options.dimmingColor)
                .imePadding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AnimatedVisibility(
                visibleState = animationState,
                enter = slideInVertically(
                    animationSpec = tween(durationMillis = animationDuration),
                ) {
                    with(density) { offsetY.dp.roundToPx() }
                },
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = animationDuration),
                ) {
                    with(density) { offsetY.dp.roundToPx() }
                },
            ) {
                when (options.type) {
                    is BottomSheetType.Draggable ->
                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = options.containerModifier
                                .noRippleClickable {}
                                .zIndex(1.0f)
                                .height(offsetY.dp),
                        ) {
                            if (options.type.isDraggable) {
                                options.dragButton?.let { button ->
                                    button(
                                        onDrag = { onDrag(it) },
                                        dragEnd = { dragEnd() },
                                    )
                                } ?: DefaultDragButton(
                                    onDrag = { delta -> onDrag(delta) },
                                    dragEnd = { dragEnd() },
                                )
                            }
                            content()
                        }

                    else ->
                        Box(
                            modifier = options.containerModifier
                                .noRippleClickable {}
                                .zIndex(1.0f),
                        ) {
                            content()
                        }
                }
            }
        }
    }
}
