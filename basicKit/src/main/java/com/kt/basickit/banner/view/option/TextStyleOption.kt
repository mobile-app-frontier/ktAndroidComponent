package com.kt.basickit.banner.view.option

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

fun TextStyle.Companion.defaultButtonTextStyle(): TextStyle {
    return TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}
