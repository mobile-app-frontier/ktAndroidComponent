package com.kt.basickit.banner.view.popupBanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.banner.view.popupBanner.button.PopupBannerButton
import com.kt.basickit.banner.view.popupBanner.content.PopupContentBannerView

@Composable
fun PopupBannerView(
    modifier: Modifier = Modifier,
    banner: PopupBannerPolicyItem,
    dismiss: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .safeContentPadding()
    ) {
        PopupContentBannerView(banner = banner, dismiss = dismiss)
        PopupBannerButton(
            bannerId = banner.id,
            closeType = banner.closeType,
            dismiss = dismiss
        )
    }
}