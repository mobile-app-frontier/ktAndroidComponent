package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

/**
 * 배너 정책
 *
 * BannerPolicyFetcher 를 통해 서버 에서 받아온 raw data 가 [BannerPolicy] 로 변환됨.
 */
public interface BannerPolicy {
    companion object {
        /**
         * 비어 있는 BannerPolicy 객체를 생성.
         *
         * @return Empty BannerPolicy
         */
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
