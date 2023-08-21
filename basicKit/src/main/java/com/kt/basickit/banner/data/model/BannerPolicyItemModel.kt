package com.kt.basickit.banner.data.model

public interface BannerPolicyItemModel {
    public val id: String
    public val priority: Int
    public val contentType: String
    public val content: String
    public val landingType: String?
    public val landingDestination: String?
    public val closeType: String?
    public val appVersion: String?
    public val category: String?
    public val type: String
    public val additionalInfo: Map<String, String>?
}