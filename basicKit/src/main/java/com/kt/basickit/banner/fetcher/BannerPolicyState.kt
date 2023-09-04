package com.kt.basickit.banner.fetcher

import com.kt.basickit.banner.LocalBannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicy

/**
 * [BannerPolicyFetcher] 의 상태
 *
 * - [Initial] 초기 상태
 * - [Fetched] remote banner policy 와 local banner policy 를 읽어온 상태.
 * - [Success] 읽어온 remote & local banner policy 를 비교 하여 보여줄 banner 를 filtering 완료한 상태.
 * - [Fail] fetch 과정 중, [Throwable] 이 발생 하여 실패한 상태.
 */
public sealed class BannerPolicyState {
    /**
     * 초기 상태
     */
    public object Initial : BannerPolicyState()

    /**
     * remote banner policy 와 local banner policy 정보를 받아온 상태.
     *
     * @property remoteBanner 서버 에서 내려준 보여줄 배너 정책.
     * @property localBanner 단말 에서 읽어온 보여 주지 않을 배너 정책.
     */
    public data class Fetched(val remoteBanner: BannerPolicy, val localBanner: LocalBannerPolicy) : BannerPolicyState()

    /**
     * 보여줄 remote banner policy 와 보여 주지 않을 local banner policy 를 비교 하여 보여줄 정책을 filtering 완료한 상태
     *
     * @property willShowBanner 실제 보여줄 배너 정책
     */
    public data class Success(val willShowBanner: BannerPolicy) : BannerPolicyState()

    /**
     * remote banner policy or local banner policy fetch 에 실패한 상태
     *
     * @property error
     */
    public data class Fail(val error: Throwable) : BannerPolicyState()

    /**
     * 보여줄 배너 정책
     *
     * @return [Success] 일 경우 에는 보여줄 배너 정책을 return. 나머지 모든 경우 에는 null 을 return.
     */
    public fun willShowBanner(): BannerPolicy? {
        return when (this) {
            is Success -> willShowBanner
            else -> null
        }
    }
}