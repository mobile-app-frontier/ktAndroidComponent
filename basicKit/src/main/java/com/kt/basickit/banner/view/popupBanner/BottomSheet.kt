package com.kt.basickit.banner.view.popupBanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheet(
    onDismiss: () -> Unit,
    hideTrigger: () -> Boolean, // 자동으로 dismiss 트리거하는 동작
    confirmValueChange: (SheetValue) -> Boolean,
    bottomSheetView: @Composable () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        /// 이 람다를 이용해서 post 등 닫히면 곤란한 작업을 수행 중일때 바텀 시트가 닫히는 것을 방지할 수 있다
        confirmValueChange = confirmValueChange
    )

    LaunchedEffect(hideTrigger()) {
        if (hideTrigger()) {
            onDismiss()
            modalBottomSheetState.hide()
        }
    }
    Box {
        ModalBottomSheet(
            containerColor = Color.White,
            windowInsets = WindowInsets.ime,
            onDismissRequest = onDismiss,
            sheetState = modalBottomSheetState,
        ) {
            Box {
                bottomSheetView()
            }
        }
    }
}