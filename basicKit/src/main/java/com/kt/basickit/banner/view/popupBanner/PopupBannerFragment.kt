package com.kt.basickit.banner.view.popupBanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kt.basickit.R
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerCloseType
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.databinding.BottomSheetBinding

internal class PopupBannerFragment() : BottomSheetDialogFragment() {
    lateinit var banner: PopupBannerPolicyItem
    override fun onDetach() {
        super.onDetach()
        context?.let { BannerManager.presentPopup(context = it) }
    }

    override fun dismiss() {
        super.dismiss()
        if (banner.closeType == BannerCloseType.CloseOnly) {
            BannerManager.saveLocalBannerPolicy(id = banner.id, notShowedDate = banner.closeType.notShowedDate)
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banner = BannerManager.getBanner()

        return DataBindingUtil.inflate<BottomSheetBinding>(
            inflater,
            R.layout.bottom_sheet,
            container,
            false
        ).apply {
            bottomSheetContentView.setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            bottomSheetContentView.setContent {
                PopupBannerView(banner = banner, dismiss = { dismiss() } )
            }
        }.root
    }
}