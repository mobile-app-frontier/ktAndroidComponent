//[basicKit](../../index.md)/[com.kt.basickit.pollingcenter](index.md)

# Package com.kt.basickit.pollingcenter

## Types

| Name | Summary |
|---|---|
| [OnPolling](index.md#-423595054%2FClasslikes%2F2043513891) | [androidJvm]<br>typealias [OnPolling](index.md#-423595054%2FClasslikes%2F2043513891) = suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>폴링에 등록할 콜백 타입 |
| [PollingCenter](-polling-center/index.md) | [androidJvm]<br>object [PollingCenter](-polling-center/index.md) |
| [WorkableItem](-workable-item/index.md) | [androidJvm]<br>data class [WorkableItem](-workable-item/index.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), pollingType: [PollingCenter.PollingType](-polling-center/-polling-type/index.md), interval: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), onPolling: [OnPolling](index.md#-423595054%2FClasslikes%2F2043513891))<br>수행할 폴링 작업에 대한 정보 폴링 센터에 작업 추가 시 이용할 것 |
