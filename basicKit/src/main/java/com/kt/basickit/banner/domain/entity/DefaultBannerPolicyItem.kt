package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.kt.basickit.util.Version

internal data class DefaultBannerPolicyItem(
    override val id: String,
    override val priority: Int,
    override val targetAppVersion: Version?,
    override val landingType: BannerLandingType,
    override val additionalInfo: Map<String, String>? = null,

    internal val content: Content,
    internal val category: String
) : BannerPolicyItem {
    companion object {
        internal fun fromModel(model: BannerPolicyItemModel): DefaultBannerPolicyItem {
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

    internal sealed class Content {
        internal data class Image(val url: String): Content()

        internal fun url(): String {
            return when (this) {
                is Image -> url
            }
        }

        companion object {
            internal fun fromString(rawValue: String): Content {
                return Image(url = rawValue)
            }
        }
    }
}