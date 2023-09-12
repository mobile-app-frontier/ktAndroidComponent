package com.kt.basickit.bottomsheet

/**
 * Bottom sheet 종류
 */
sealed class BottomSheetType {
    sealed class Draggable(
        open val isDraggable: Boolean = true,
    ) : BottomSheetType()

    /**
     * 화면 꽉차는 높이의 BottomSheet
     */
    data class Full(
        override val isDraggable: Boolean = true,
    ) : Draggable()

    /**
     * 화면 절반 높이의 BottomSheet
     */
    data class Half(
        override val isDraggable: Boolean = true,
    ) : Draggable()

    /**
     * 지정한 높이 만큼의 BottomSheet
     */
    data class Custom(
        val minHeight: Float,
        val maxHeight: Float? = null,
        override val isDraggable: Boolean = true,
    ) : Draggable()

    /**
     * Contents 의 높이 만큼 올라오는 가장 기본 BottomSheet Drag 안됨.
     */
    object Default : BottomSheetType()
}
