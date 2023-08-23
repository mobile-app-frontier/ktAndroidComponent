package com.kt.basickit.banner.fetcher

import com.kt.basickit.banner.LocalBannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicy

public sealed class BannerPolicyState {
    public object Initial : BannerPolicyState()

    public data class Fetched(val remoteBanner: BannerPolicy, val localBanner: LocalBannerPolicy) : BannerPolicyState()

    public data class Success(val willShowBanner: BannerPolicy) : BannerPolicyState()

    public data class Fail(val error: Throwable) : BannerPolicyState()

    public fun willShowBanner(): BannerPolicy? {
        return when (this) {
            is Success -> willShowBanner
            else -> null
        }
    }
}