package com.example.bannerexampleapp.domain.usecase

import com.example.bannerexampleapp.data.source.BannerPolicyDataSourceImpl
import com.kt.basickit.banner.domain.entity.BannerPolicy
import com.kt.basickit.banner.domain.repository.BannerPolicyRepository

class BannerPolicyUseCase constructor(
    private val dataSource: BannerPolicyDataSourceImpl
): UseCase, BannerPolicyRepository {
    override suspend fun getBannerPolicy(): BannerPolicy {
        val data = dataSource.getBannerPolicy()

        return BannerPolicy.fromModel(model = data)
    }
}