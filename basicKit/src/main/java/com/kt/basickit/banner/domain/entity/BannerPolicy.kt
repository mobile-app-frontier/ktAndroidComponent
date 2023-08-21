package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

public data class BannerPolicy(
    val defaultBanner: DefaultBannerPolicy,
    val popupBanner: PopupBannerPolicy
) {
    companion object {
        public fun fromModel(model: List<BannerPolicyItemModel>): BannerPolicy {
            return BannerPolicy(
                defaultBanner = model.toDefaultBannerPolicy(),
                popupBanner = model.toPopupBannerPolicy()
            )
        }
    }
}
