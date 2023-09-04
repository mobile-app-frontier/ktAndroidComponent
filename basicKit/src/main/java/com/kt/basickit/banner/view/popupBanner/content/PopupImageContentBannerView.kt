package com.kt.basickit.banner.view.popupBanner.content

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem

@Composable
internal fun PopupImageContentBannerView(modifier: Modifier = Modifier, content: PopupBannerPolicyItem.Content.Image) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(content.url)
            .build(),
        contentDescription = null,
        contentScale =  if (isPortrait) ContentScale.FillWidth else ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = configuration.screenHeightDp.dp - 150.dp),
    )
}