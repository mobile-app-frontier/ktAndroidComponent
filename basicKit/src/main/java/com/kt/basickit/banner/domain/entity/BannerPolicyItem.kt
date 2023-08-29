package com.kt.basickit.banner.domain.entity

import com.kt.basickit.util.Version
import java.util.Calendar
import java.util.Date

internal interface BannerPolicyItem {
    val id: String
    val priority: Int
    val targetAppVersion: Version?
    val landingType: BannerLandingType
    val additionalInfo: Map<String, String>?
}

internal enum class BannerCloseType {
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
        internal fun fromString(rawValue: String?): BannerCloseType {
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



