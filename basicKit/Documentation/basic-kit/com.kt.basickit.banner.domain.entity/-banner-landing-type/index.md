//[basicKit](../../../index.md)/[com.kt.basickit.banner.domain.entity](../index.md)/[BannerLandingType](index.md)

# BannerLandingType

[androidJvm]\
sealed class [BannerLandingType](index.md) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

이동할 화면에 대한 정보.

- 
   [None](-none/index.md): 이동 하지 않음.
- 
   [Web](-web/index.md): 이동할 web view 에 대한 정보.
- 
   [InApp](-in-app/index.md)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |
| [InApp](-in-app/index.md) | [androidJvm]<br>data class [InApp](-in-app/index.md)(destination: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerLandingType](index.md)<br>앱 내부의 화면 으로 랜딩. |
| [None](-none/index.md) | [androidJvm]<br>object [None](-none/index.md) : [BannerLandingType](index.md)<br>이동 하지 않음. |
| [Web](-web/index.md) | [androidJvm]<br>data class [Web](-web/index.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [BannerLandingType](index.md)<br>Web view 로 랜딩. |

## Functions

| Name | Summary |
|---|---|
| [describeContents](../../com.kt.basickit.util/-version/index.md#-1578325224%2FFunctions%2F2043513891) | [androidJvm]<br>abstract fun [describeContents](../../com.kt.basickit.util/-version/index.md#-1578325224%2FFunctions%2F2043513891)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](../../com.kt.basickit.util/-version/index.md#-1754457655%2FFunctions%2F2043513891) | [androidJvm]<br>abstract fun [writeToParcel](../../com.kt.basickit.util/-version/index.md#-1754457655%2FFunctions%2F2043513891)(p0: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Inheritors

| Name |
|---|
| [None](-none/index.md) |
| [Web](-web/index.md) |
| [InApp](-in-app/index.md) |
