package com.kt.basickit.bottomsheet

/**
 * Bottom sheet 종류
 */
sealed class BottomSheetType
sealed class DraggableType(
    open val isDraggable: Boolean = true,
) : BottomSheetType()
/**
 * 화면 꽉차는 높이의 BottomSheet
 */
data class FullBottomSheet(
    override val isDraggable: Boolean = true,
) : DraggableType()

/**
 * 화면 절반 높이의 BottomSheet
 */
data class HalfBottomSheet(
    override val isDraggable: Boolean = true,
) : DraggableType()

/**
 * 지정한 높이 만큼의 BottomSheet
 */
data class CustomBottomSheet(
    val height: Float,
    override val isDraggable: Boolean = true,
) : DraggableType()

/**
 * Contents 의 높이 만큼 올라오는 가장 기본 BottomSheet Drag 안됨.
 */
object DefaultBottomSheet : BottomSheetType()
