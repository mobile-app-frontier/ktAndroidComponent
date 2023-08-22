package com.example.bannerexampleapp.data.model

import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.squareup.moshi.Json

data class BannerPolicyItemModelImpl(
    @Json(name = "id")
    override val id: String,
    @Json(name = "priority")
    override val priority: Int,
    @Json(name = "contentType")
    override val contentType: String,
    @Json(name = "content")
    override val content: String,
    @Json(name = "landingType")
    override val landingType: String? = null,
    @Json(name = "landingDestination")
    override val landingDestination: String? = null,
    @Json(name = "closeType")
    override val closeType: String? = null,
    @Json(name = "appVersion")
    override val appVersion: String? = null,
    @Json(name = "category")
    override val category: String?,
    @Json(name = "type")
    override val type: String,
    @Json(name = "additionalInfo")
    override val additionalInfo: Map<String, String>? = null
) : BannerPolicyItemModel {
}