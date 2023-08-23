package com.kt.basickit.banner.view.popupBanner.button

import androidx.compose.runtime.Composable
import com.kt.basickit.banner.domain.entity.BannerCloseType

@Composable
internal fun PopupBannerButton(
    bannerId: String,
    closeType: BannerCloseType,
    dismiss: () -> Unit
) {
    when (closeType) {
        BannerCloseType.CloseOnly -> PopupBannerCloseOnlyButton(dismiss = dismiss)
        BannerCloseType.NotShowToday -> PopupBannerOptionButton(
            bannerId = bannerId,
            closeType = closeType,
            dismiss = dismiss
        )
        BannerCloseType.NotShowForWeek -> PopupBannerOptionButton(
            bannerId = bannerId,
            closeType = closeType,
            dismiss = dismiss
        )
        BannerCloseType.NeverShowAgain -> PopupBannerOptionButton(
            bannerId = bannerId,
            closeType = closeType,
            dismiss = dismiss
        )
    }
}