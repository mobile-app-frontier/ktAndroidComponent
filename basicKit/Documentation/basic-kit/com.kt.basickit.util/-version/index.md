//[basicKit](../../../index.md)/[com.kt.basickit.util](../index.md)/[Version](index.md)

# Version

[androidJvm]\
data class [Version](index.md)(major: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), minor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), patch: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), preRelease: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[Version](index.md)&gt; , [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

[Semantic Versioning](https://semver.org/)

지원 하는 버전 format: "$major.$minor.$patch(-$preRelease)" ex) 1.6.1 or 2.10.4-beta

<em>[Version](index.md) 비교 시에는 [preRelease](pre-release.md) 는 확인 하지 않는다. 지원 하지 않는 format 인 경우 Version(0,0,0,null) 를 return 한다.</em>

## Constructors

| | |
|---|---|
| [Version](-version.md) | [androidJvm]<br>fun [Version](-version.md)(major: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), minor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), patch: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), preRelease: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [compareTo](compare-to.md) | [androidJvm]<br>open operator override fun [compareTo](compare-to.md)(other: [Version](index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>[major](major.md) ->[minor](minor.md) ->[patch](patch.md) 순으로 비교. 주의! [preRelease](pre-release.md) 는 비교시 사용 되지 않는다. |
| [describeContents](index.md#-1578325224%2FFunctions%2F2043513891) | [androidJvm]<br>abstract fun [describeContents](index.md#-1578325224%2FFunctions%2F2043513891)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](index.md#-1754457655%2FFunctions%2F2043513891) | [androidJvm]<br>abstract fun [writeToParcel](index.md#-1754457655%2FFunctions%2F2043513891)(p0: [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [major](major.md) | [androidJvm]<br>val [major](major.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>version when you mamke incompatible API changes |
| [minor](minor.md) | [androidJvm]<br>val [minor](minor.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>version when you add functionality in a backward compatible manner |
| [patch](patch.md) | [androidJvm]<br>val [patch](patch.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>version when you make backward compatible bug fixes |
| [preRelease](pre-release.md) | [androidJvm]<br>val [preRelease](pre-release.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Identifiers MUST comprise only ASCII alphanumerics and hyphens 0-9A-Za-z-. Identifiers MUST NOT be empty. Numeric identifiers MUST NOT include leading zeroes. Pre-release versions have a lower precedence than the associated normal version. A pre-release version indicates that the version is unstable and might not satisfy the intended compatibility requirements as denoted by its associated normal version. Examples: 1.0.0-alpha, 1.0.0-alpha.1, 1.0.0-0.3.7, 1.0.0-x.7.z.92, 1.0.0-x-y-z. |
