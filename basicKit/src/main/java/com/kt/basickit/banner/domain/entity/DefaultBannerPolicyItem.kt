package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.kt.basickit.util.Version

public data class DefaultBannerPolicyItem(
    public override val id: String,
    public override val priority: Int,
    public override val targetAppVersion: Version?,
    public override val landingType: BannerLandingType,
    public override val additionalInfo: Map<String, String>? = null,

    public val content: Content,
    public val category: String
) : BannerPolicyItem {
    companion object {
        fun fromModel(model: BannerPolicyItemModel): DefaultBannerPolicyItem {
            return DefaultBannerPolicyItem(
                id = model.id,
                priority = model.priority,
                targetAppVersion = model.appVersion?.let { Version.createVersion(it) },
                landingType = BannerLandingType.fromString(model.landingType, url = model.landingDestination),
                content = Content.fromString(model.content),
                category = model.category ?: "",
                additionalInfo = model.additionalInfo
            )
        }
    }

    public sealed class Content {
        public data class Image(val url: String): Content()

        fun url(): String {
            return when (this) {
                is Image -> url
            }
        }

        companion object {
            fun fromString(rawValue: String): Content {
                return Image(url = rawValue)
            }
        }
    }
}