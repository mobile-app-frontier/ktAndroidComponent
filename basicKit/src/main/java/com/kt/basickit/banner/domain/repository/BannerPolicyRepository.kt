package com.kt.basickit.banner.domain.repository

import com.kt.basickit.banner.domain.entity.BannerPolicy

public interface BannerPolicyRepository {
    public suspend fun getBannerPolicy(): BannerPolicy
}
