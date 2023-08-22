package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

typealias DefaultBannerPolicy = Map<String, List<DefaultBannerPolicyItem>>

fun List<BannerPolicyItemModel>.toDefaultBannerPolicy(): DefaultBannerPolicy {
    return this
        .filter { it.type == "default" && it.category != null }
        .groupBy({ it.category!! }, { DefaultBannerPolicyItem.fromModel(model = it) })
}