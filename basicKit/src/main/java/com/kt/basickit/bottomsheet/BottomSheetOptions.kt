package com.kt.basickit.bottomsheet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Bottom sheet options
 *
 * @property isDraggable: [BottomSheetType] 이 [FullBottomSheet], [HalfBottomSheet], [CustomBottomSheet] 일 경우에 상단 드래그 바로 높이 조절 할 수 있음.
 * @property dimmingColor: BottomSheet 배경 Dimming 색깔
 * @property borderRadius: BottomSheet 좌, 우측 상단 BorderRadius 값
 * @property contentBackgroundColor
 * @property contentPadding: 내부 Content 의 패딩
 * @property contentWidth: 가로 크기의 비율. ex) 0.7f 일 경우 화면 가로 사이즈 70% 크기의 BottomSheet 가 만들어 짐.
 * @constructor 모든 요소는 기본 값이 있어서 따로 지정 하지 않으면 기본 BottomSheet 디자인이 적용됨.
 */
data class BottomSheetOptions(
    val isDraggable: Boolean = true,
    val dimmingColor: Color = Color.Gray.copy(alpha = 0.2f),
    val borderRadius: Int = 10,
    val contentBackgroundColor: Color = Color.White,
    val contentPadding: PaddingValues = PaddingValues(20.dp, 10.dp),
    val contentWidth: Float = 1f, // 쓸까..?\
)