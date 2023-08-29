package com.kt.basickit.banner.view.popupBanner.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerCloseType

@Composable
internal fun PopupBannerOptionButton(
    bannerId: String, closeType: BannerCloseType,
    dismiss: () -> Unit
) {
    val buttonTextStyle = BannerManager.buttonTextStyle

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            closeType.text,
            style = buttonTextStyle,
            modifier = Modifier
                .clickable {
                    BannerManager.saveLocalBannerPolicy(
                        id = bannerId,
                        notShowedDate = closeType.notShowedDate)
                    dismiss()
                }
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .padding(end = 10.dp)
        )
        Text(
            "닫기",
            style = buttonTextStyle,
            modifier = Modifier
                .clickable {
                    dismiss()
                }
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .padding(start = 10.dp)
        )
    }
}