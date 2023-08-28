package com.kt.basickit.banner.view.defaultBanner

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.kt.basickit.R
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.DefaultBannerPolicyItem

public class DefaultBannerFragment(): Fragment() {
    private lateinit var banners: List<DefaultBannerPolicyItem>

    internal constructor(category: String) : this() {
        banners = BannerManager.getDefaultBanners(category)
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultBannerOption)

        val category = typedArray.getString(R.styleable.DefaultBannerOption_category)

        category?.let {
            banners = BannerManager.getDefaultBanners(category)
        }
        typedArray.recycle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                if (::banners.isInitialized) {
                    DefaultBannerView(banners = banners)
                } else {
                    Box(modifier = Modifier)
                }
            }
        }
    }
}