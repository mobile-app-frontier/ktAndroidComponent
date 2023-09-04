//[basicKit](../../../index.md)/[com.kt.basickit.banner.data.model](../index.md)/[BannerPolicyItemModel](index.md)

# BannerPolicyItemModel

[androidJvm]\
interface [BannerPolicyItemModel](index.md)

서버 에서 받아온 BannerPolicy Item 의 raw 정보 Model.

## Properties

| Name | Summary |
|---|---|
| [additionalInfo](additional-info.md) | [androidJvm]<br>abstract val [additionalInfo](additional-info.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?<br>extra format. |
| [appVersion](app-version.md) | [androidJvm]<br>abstract val [appVersion](app-version.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>해당 배너를 보여줄 AppVersion. null 일 경우 모든 앱버전 에서 보여짐. |
| [category](category.md) | [androidJvm]<br>abstract val [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Default Banner 에서 사용 되는 Banner 의 Category. |
| [closeType](close-type.md) | [androidJvm]<br>abstract val [closeType](close-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>PopupBanner 를 닫을 때의 옵션. |
| [content](content.md) | [androidJvm]<br>abstract val [content](content.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>배너 내용. [contentType](content-type.md) 에 따라 다른 내용을 지님. |
| [contentType](content-type.md) | [androidJvm]<br>abstract val [contentType](content-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>배너 Content 의 종류 |
| [id](id.md) | [androidJvm]<br>abstract val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>배너 ID |
| [landingDestination](landing-destination.md) | [androidJvm]<br>abstract val [landingDestination](landing-destination.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>[landingType](landing-type.md) 이 "web" 일 경우, 이동할 web url. |
| [landingType](landing-type.md) | [androidJvm]<br>abstract val [landingType](landing-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>landing 할 화면 혹은 "web". landing 하지 않을 경우 null or "none". |
| [priority](priority.md) | [androidJvm]<br>abstract val [priority](priority.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>배너의 우선 순위. (높을 수록 먼저 보여짐.) |
| [type](type.md) | [androidJvm]<br>abstract val [type](type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>배너의 종류. |
