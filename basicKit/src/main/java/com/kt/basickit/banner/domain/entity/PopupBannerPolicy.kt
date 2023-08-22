package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import java.util.PriorityQueue

public typealias PopupBannerPolicy = PriorityQueue<PopupBannerPolicyItem>

public fun List<BannerPolicyItemModel>.toPopupBannerPolicy(): PopupBannerPolicy {
    return PopupBannerPolicy(
        this
            .filter { it.type == "popup" }
            .map { PopupBannerPolicyItem.fromModel(it) }
    )
}