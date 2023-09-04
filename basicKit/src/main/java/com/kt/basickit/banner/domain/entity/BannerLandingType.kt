package com.kt.basickit.banner.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 이동할 화면에 대한 정보.
 *
 * - [None]: 이동 하지 않음.
 * - [Web]: 이동할 web view 에 대한 정보.
 * - [InApp]
 */
@Parcelize
public sealed class BannerLandingType : Parcelable {
    /**
     * 이동 하지 않음.
     */
    public object None: BannerLandingType()

    /**
     * Web view 로 랜딩.
     *
     * @property url: landing 할 url.
     */
    public data class Web(val url: String): BannerLandingType()

    /**
     * 앱 내부의 화면 으로 랜딩.
     *
     * @property destination: 앱 내부 에서 이동할 화면의 이름.
     */
    public data class InApp(val destination: String): BannerLandingType()

    companion object {
        internal fun fromString(rawValue: String?, url: String?): BannerLandingType {
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