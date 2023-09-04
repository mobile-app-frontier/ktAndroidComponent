package com.kt.basickit.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Compose Modifier 확장 함수. 클릭 효과 없애고 싶을 때 사용.
 * clickable 대신 사용할 수 있음.
 *
 * @param onClick
 * @return [Modifier]
 */
internal inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        onClick()
    }
}