package com.kt.basickit.banner.view.popupBanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerCloseType
import com.kt.basickit.banner.view.popupBanner.button.PopupBannerButton
import com.kt.basickit.banner.view.popupBanner.content.PopupContentBannerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupBannerView(modifier: Modifier = Modifier) {
    val popupBanner by BannerManager.popupBanner.collectAsState()
    val scrollState = rememberScrollState()  // TODO: 제공 해야 할까...?

    popupBanner?.let{
        BottomSheet(
            bottomSheetView = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .safeContentPadding()
                        .verticalScroll(scrollState)
                ) {
                    PopupContentBannerView(
                        banner = it
                    )
                    PopupBannerButton(bannerId = it.id, closeType = it.closeType)
                }
            },
            onDismiss = {
                BannerManager.dismissAndPresentPopup(
                    id = it.id,
                    notShowedDate = if (it.closeType == BannerCloseType.CloseOnly) it.closeType.notShowedDate else null
                )
            },
            hideTrigger = { false },
            confirmValueChange = { true }
        )
    }
}