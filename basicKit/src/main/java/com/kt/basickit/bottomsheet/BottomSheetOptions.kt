package com.kt.basickit.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val borderRadius: Int = 10
val contentPadding: PaddingValues = PaddingValues(20.dp, 10.dp)
val contentBackgroundColor: Color = Color.White

typealias HandleDrag = (Float) -> Unit

/**
 * Bottom sheet options
 *
 * @property type BottomSheet 종류
 * @property dimmingColor: BottomSheet 배경 Dimming 색깔
 * @property containerModifier: BottomSheet 컨테이너의 UI 디자인 modifier
 * @constructor 모든 요소는 기본 값이 있어서 따로 지정 하지 않으면 기본 BottomSheet 디자인이 적용됨.
 */
data class BottomSheetOptions(
    val type: BottomSheetType = BottomSheetType.Default,
    val dimmingColor: Color = Color.Gray.copy(alpha = 0.2f),
    val containerModifier: Modifier = Modifier
        .background(
            contentBackgroundColor,
            shape = RoundedCornerShape(
                borderRadius,
                borderRadius,
                0,
                0,
            ),
        )
        .padding(contentPadding),
    val dragButton: (@Composable (onDrag: HandleDrag, dragEnd: () -> Unit) -> Unit)? = null,
)
