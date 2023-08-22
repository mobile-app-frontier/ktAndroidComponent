package com.kt.basickit.banner.view.popupBanner.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem

@Composable
internal fun PopupTextContentBannerView(content: PopupBannerPolicyItem.Content.Text) {
    Text(
        content.text,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    )
}