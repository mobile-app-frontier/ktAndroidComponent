package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

public interface BannerPolicy {
    companion object {
        public fun create(): BannerPolicy {
            return BannerPolicyImpl.create()
        }

        internal fun fromModel(model: List<BannerPolicyItemModel>): BannerPolicyImpl {
            return BannerPolicyImpl.fromModel(model)
        }
    }
}

internal data class BannerPolicyImpl(
    internal val defaultBanner: DefaultBannerPolicy,
    internal val popupBanner: PopupBannerPolicy
) : BannerPolicy {
    companion object {
        internal fun create(): BannerPolicyImpl {
            return BannerPolicyImpl(
                defaultBanner = mapOf(),
                popupBanner = PopupBannerPolicy()
            )
        }
        internal fun fromModel(model: List<BannerPolicyItemModel>): BannerPolicyImpl {
            return BannerPolicyImpl(
                defaultBanner = model.toDefaultBannerPolicy(),
                popupBanner = model.toPopupBannerPolicy()
            )
        }
    }
}
