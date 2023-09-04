package com.kt.basickit.banner.data.source

import com.kt.basickit.banner.data.model.BannerPolicyItemModel

/**
 * BannerPolicy 를 서버 에서 받아올 때 사용 되는 DataSource Interface
 *
 */
public interface BannerPolicyDataSource {
    /**
     * 서버 에서 BannerPolicy Json 정보를 받아와 List<BannerPolicyItemModel> 로 변환 하여 반환 하는 로직
     *
     * @return [BannerPolicyItemModel] 의 리스트.
     */
    public suspend fun getBannerPolicy() : List<BannerPolicyItemModel>
}