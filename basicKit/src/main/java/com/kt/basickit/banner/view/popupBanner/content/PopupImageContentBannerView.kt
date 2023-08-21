package com.kt.basickit.banner.view.popupBanner.content

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem

@Composable
fun PopupImageContentBannerView(content: PopupBannerPolicyItem.Content.Image) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(content.url)
            .build(),
        contentDescription = null,
        contentScale =  if (isPortrait) ContentScale.FillWidth else ContentScale.FillHeight,
        modifier = if (isPortrait) Modifier.fillMaxWidth() else Modifier
    )
}