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

@Composable
internal fun PopupBannerCloseOnlyButton(
    dismiss: () -> Unit,
) {
    val buttonTextStyle = BannerManager.buttonTextStyle

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
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