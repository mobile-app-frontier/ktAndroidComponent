package com.kt.basickit.banner.view.popupBanner.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerLandingType
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem

@Composable
internal fun PopupContentBannerView(banner: PopupBannerPolicyItem) {
    Box(
        modifier = Modifier
            .clickable(enabled = banner.landingType !is BannerLandingType.None) {
                BannerManager.sendToLandingType(landingType = banner.landingType)
            }
    ) {
        when (banner.content) {
            is PopupBannerPolicyItem.Content.Text -> PopupTextContentBannerView(content = banner.content)
            is PopupBannerPolicyItem.Content.Image -> PopupImageContentBannerView(content = banner.content)
            is PopupBannerPolicyItem.Content.Html -> PopupHtmlContentBannerView(
                content = banner.content,
                landingType = banner.landingType
            )
        }
    }
}