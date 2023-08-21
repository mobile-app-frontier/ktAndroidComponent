package com.kt.basickit.banner.data.source

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

public interface BannerPolicyDataSource {
    public suspend fun getBannerPolicy() : List<BannerPolicyItemModel>
}