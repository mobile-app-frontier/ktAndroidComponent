package com.kt.basickit.banner.domain.entity

import com.kt.basickit.util.Version
import java.util.Calendar
import java.util.Date

public interface BannerPolicyItem {
    public val id: String
    public val priority: Int
    public val targetAppVersion: Version?
    public val landingType: BannerLandingType
    public val additionalInfo: Map<String, String>?
}

public enum class BannerCloseType {
    CloseOnly,
    NeverShowAgain,
    NotShowForWeek,
    NotShowToday
    ;

    internal val notShowedDate: Date
        get() {
            return when (this) {
                CloseOnly -> Date(Long.MAX_VALUE)
                NeverShowAgain -> Date(Long.MAX_VALUE)
                NotShowToday -> Calendar.getInstance().apply {
                    add(Calendar.DATE, 1)
                }.time
                NotShowForWeek -> Calendar.getInstance().apply {
                    add(Calendar.DATE, 7)
                }.time
            }
        }

    internal val text: String
        get() {
            return when (this) {
                CloseOnly -> ""
                NeverShowAgain -> "다시 보지 않기"
                NotShowForWeek -> "일주일 간 보지 않기"
                NotShowToday -> "오늘은 보지 않기"
            }
        }

    companion object {
        public fun fromString(rawValue: String?): BannerCloseType {
            return when (rawValue) {
                "close" -> CloseOnly
                "never" -> NeverShowAgain
                "week" -> NotShowForWeek
                "today" -> NotShowToday
                else -> CloseOnly
            }
        }
    }
}

public sealed class BannerLandingType {
    public object None: BannerLandingType()
    public data class Web(val url: String): BannerLandingType()
    public data class InApp(val destination: String): BannerLandingType()

    companion object {
        fun fromString(rawValue: String?, url: String?): BannerLandingType {
            return when (rawValue) {
                null -> None
                "" -> None
                "none" -> None
                "web" -> url?.let { Web(url = it) } ?: None
                else -> InApp(destination = rawValue)
            }
        }
    }
}


