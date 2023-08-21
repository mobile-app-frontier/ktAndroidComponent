package com.kt.basickit.banner.domain.entity

import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.kt.basickit.util.Version

public data class PopupBannerPolicyItem(
    public override val id: String,
    public override val priority: Int,
    public override val targetAppVersion: Version?,
    public override val landingType: BannerLandingType,
    public override val additionalInfo: Map<String, String>? = null,

    public val content: Content,
    public val closeType: BannerCloseType
) : Comparable<PopupBannerPolicyItem>, BannerPolicyItem {
    companion object {
        fun fromModel(model: BannerPolicyItemModel): PopupBannerPolicyItem {
            return PopupBannerPolicyItem(
                id = model.id,
                priority = model.priority,
                targetAppVersion = model.appVersion?.let { Version.createVersion(it) },
                landingType = BannerLandingType.fromString(model.landingType, url = model.landingDestination),
                content = Content.fromModel(type = model.contentType, content = model.content),
                closeType = BannerCloseType.fromString(model.closeType),
                additionalInfo = model.additionalInfo
            )
        }
    }

    public sealed class Content {
        data class Text(val text: String) : Content()

        data class Html(val html: String) : Content()

        data class Image(val url: String): Content()

        companion object {
            fun fromModel(type: String, content: String): Content {
                return when (type) {
                    "T" -> Text(content)
                    "H" -> Html(content)
                    "I" -> Image(content)
                    else -> Text(content)
                }
            }
        }
    }

    override fun compareTo(other: PopupBannerPolicyItem): Int {
        if (this.id == other.id) return 0

        return other.priority - this.priority
    }
}