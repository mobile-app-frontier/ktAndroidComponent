package com.kt.basickit.banner.view.option

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * Default Banner Button 의 default text style
 *
 * @return
 */
fun TextStyle.Companion.defaultButtonTextStyle(): TextStyle {
    return TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )
}

/**
 * Default Banner Text 의 default text style
 *
 * @return
 */
fun TextStyle.Companion.defaultBannerTextStyle(): TextStyle {
    return TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Start
    )
}
