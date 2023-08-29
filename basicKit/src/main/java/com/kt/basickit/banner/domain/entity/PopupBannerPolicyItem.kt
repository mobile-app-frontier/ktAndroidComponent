package com.kt.basickit.banner.domain.entity

import android.os.Parcelable
import com.kt.basickit.banner.data.model.BannerPolicyItemModel
import com.kt.basickit.util.Version
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class PopupBannerPolicyItem(
    override val id: String,
    override val priority: Int,
    override val targetAppVersion: Version?,
    override val landingType: BannerLandingType,
    override val additionalInfo: Map<String, String>? = null,

    internal val content: Content,
    internal val closeType: BannerCloseType
) : Comparable<PopupBannerPolicyItem>, BannerPolicyItem, Parcelable {
    companion object {
        internal fun fromModel(model: BannerPolicyItemModel): PopupBannerPolicyItem {
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

    @Parcelize
    internal sealed class Content : Parcelable {
        data class Text(val text: String) : Content()

        data class Html(val html: String) : Content()

        data class Image(val url: String): Content()

        companion object {
            internal fun fromModel(type: String, content: String): Content {
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