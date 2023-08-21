package com.kt.basickit.banner.view.popupBanner.content

import android.webkit.WebView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerLandingType
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem

@Composable
fun PopupHtmlContentBannerView(content: PopupBannerPolicyItem.Content.Html, landingType: BannerLandingType) {
    // Get local density from composable
    val localDensity = LocalDensity.current

    // Create element height in dp state
    var htmlHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Box {
        AndroidView(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    htmlHeightDp = with(localDensity) { coordinates.size.height.toDp() }
                },
            factory = { context ->
                WebView(context).apply {
                    loadData(content.html, "text/html", "UTF-8")
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(htmlHeightDp)
                .clickable(enabled = landingType !is BannerLandingType.None) {
                    BannerManager.sendToLandingType(landingType = landingType)
                }
        ) {}
    }
}