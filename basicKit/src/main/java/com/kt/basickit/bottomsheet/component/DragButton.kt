package com.kt.basickit.bottomsheet.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * BottomSheet 상단에 높이 조절 할 때 사용 하는 Drag 버튼
 *
 * @param onDrag
 */
@Composable
fun DragButton(
    onDrag: (Float) -> Unit,
    dragEnd: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 20.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        dragEnd()
                    },
                ) { change, dragAmount ->
                    if (change.pressed) {
                        onDrag(dragAmount.y)
                    }
                    change.consume()
                }
            },
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
