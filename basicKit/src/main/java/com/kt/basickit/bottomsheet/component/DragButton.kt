package com.kt.basickit.bottomsheet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * BottomSheet 상단 Drag 하는 회색 버튼
 *
 * @param handleDrag
 * @receiver
 */
@Composable
fun DragButton(handleDrag: (Float) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    handleDrag(delta)
                },
            ),
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(
                        50,
                        50,
                        50,
                        50,
                    ),
                )
                .width(100.dp)
                .height(5.dp)
                .align(Alignment.Center),
        )
    }
}
