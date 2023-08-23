package com.kt.basickit.banner.view.option

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextStyleOption(
    val fontFamily: FontFamily? = null,
    val fontWeight: FontWeight = FontWeight.Normal,
    val fontSize: TextUnit = 16.sp,
    val color: Color = Color.Black,
    val textAlign: TextAlign = TextAlign.Center
) {
    companion object {
        fun defaultButtonTextStyle(): TextStyleOption {
            return TextStyleOption(
                fontFamily = null,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
