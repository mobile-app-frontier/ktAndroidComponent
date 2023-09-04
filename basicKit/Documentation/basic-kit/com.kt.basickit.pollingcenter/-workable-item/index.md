//[basicKit](../../../index.md)/[com.kt.basickit.pollingcenter](../index.md)/[WorkableItem](index.md)

# WorkableItem

[androidJvm]\
data class [WorkableItem](index.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), pollingType: [PollingCenter.PollingType](../-polling-center/-polling-type/index.md), interval: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), onPolling: [OnPolling](../index.md#-423595054%2FClasslikes%2F2043513891))

수행할 폴링 작업에 대한 정보 폴링 센터에 작업 추가 시 이용할 것

## Parameters

androidJvm

| | |
|---|---|
| key | 폴링 작업 구분애 이용할 고유키 |
| pollingType | 폴링 작업 추가 시 바로 실행 할 지 여부 |
| interval | 폴링 작업 실행 주기 |
| onPolling | 수행할 폴링 작업 |

## Constructors

| | |
|---|---|
| [WorkableItem](-workable-item.md) | [androidJvm]<br>fun [WorkableItem](-workable-item.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), pollingType: [PollingCenter.PollingType](../-polling-center/-polling-type/index.md), interval: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), onPolling: [OnPolling](../index.md#-423595054%2FClasslikes%2F2043513891)) |

## Types

| Name | Summary |
|---|---|
| [DefaultWorkableItem](-default-workable-item/index.md) | [androidJvm]<br>object [DefaultWorkableItem](-default-workable-item/index.md)<br>디폴트 폴링 주기를 사용 하는 경우 |

## Properties

| Name | Summary |
|---|---|
| [interval](interval.md) | [androidJvm]<br>val [interval](interval.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [key](key.md) | [androidJvm]<br>val [key](key.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [onPolling](on-polling.md) | [androidJvm]<br>val [onPolling](on-polling.md): [OnPolling](../index.md#-423595054%2FClasslikes%2F2043513891) |
| [pollingType](polling-type.md) | [androidJvm]<br>val [pollingType](polling-type.md): [PollingCenter.PollingType](../-polling-center/-polling-type/index.md) |
