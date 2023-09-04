package com.kt.basickit.banner.domain.repository

import com.kt.basickit.banner.data.source.BannerPolicyDataSource
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.entity.BannerPolicyImpl

internal class BannerPolicyRepository(private val dataSource: BannerPolicyDataSource) {
    internal suspend fun getBannerPolicy(): BannerPolicyImpl {
        val data = dataSource.getBannerPolicy()

        return BannerPolicy.fromModel(model = data)
    }
}
