package com.kt.basickit.bottomsheet

/**
 * Bottom sheet 종류
 */
sealed class BottomSheetType

/**
 * 화면 꽉차는 높이의 BottomSheet
 */
object FullBottomSheet : BottomSheetType()

/**
 * 화면 절반 높이의 BottomSheet
 */
object HalfBottomSheet : BottomSheetType()

/**
 * 지정한 높이 만큼의 BottomSheet
 */
data class CustomBottomSheet(val height: Float) : BottomSheetType()

/**
 * Contents 의 높이 만큼 올라오는 가장 기본 BottomSheet Drag 안됨.
 */
object DefaultBottomSheet : BottomSheetType()
