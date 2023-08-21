package com.kt.basickit.banner.view.popupBanner.button

import androidx.compose.runtime.Composable
import com.kt.basickit.banner.domain.entity.BannerCloseType

@Composable
internal fun PopupBannerButton(bannerId: String, closeType: BannerCloseType) {
    when (closeType) {
        BannerCloseType.CloseOnly -> PopupBannerCloseOnlyButton(bannerId = bannerId)
        BannerCloseType.NotShowToday -> PopupBannerOptionButton(bannerId = bannerId, closeType = closeType)
        BannerCloseType.NotShowForWeek -> PopupBannerOptionButton(bannerId = bannerId, closeType = closeType)
        BannerCloseType.NeverShowAgain -> PopupBannerOptionButton(bannerId = bannerId, closeType = closeType)
    }
}