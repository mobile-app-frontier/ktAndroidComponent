package com.kt.basickit.banner.view.defaultBanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.kt.basickit.banner.BannerManager

internal class DefaultBannerFragment(category: String): Fragment() {
    private val banners = BannerManager.getDefaultBanners(category)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                DefaultBannerView(banners = banners)
            }
        }
    }
}