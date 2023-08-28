package com.kt.basickit.banner.view.popupBanner

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kt.basickit.R
import com.kt.basickit.banner.BannerManager
import com.kt.basickit.banner.domain.entity.BannerCloseType
import com.kt.basickit.banner.domain.entity.PopupBannerPolicyItem
import com.kt.basickit.databinding.BottomSheetBinding

internal class PopupBannerFragment : BottomSheetDialogFragment() {
    companion object {
        private const val POPUP_BANNER_POLICY_ITEM_KEY = "popup_banner_policy_item"
    }

    lateinit var banner: PopupBannerPolicyItem
    override fun onDetach() {
        super.onDetach()
        context?.let { BannerManager.presentPopup(context = it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(POPUP_BANNER_POLICY_ITEM_KEY, banner)
    }

    override fun dismiss() {
        super.dismiss()
        if (banner.closeType == BannerCloseType.CloseOnly) {
            BannerManager.saveLocalBannerPolicy(id = banner.id, notShowedDate = banner.closeType.notShowedDate)
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        banner = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState
                ?.getParcelable(POPUP_BANNER_POLICY_ITEM_KEY, PopupBannerPolicyItem::class.java)
                ?: BannerManager.getBanner()
        } else {
            savedInstanceState
                ?.getParcelable(POPUP_BANNER_POLICY_ITEM_KEY) ?: BannerManager.getBanner()
        }

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