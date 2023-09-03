//[basicKit](../../../../index.md)/[com.kt.basickit.util](../../index.md)/[Version](../index.md)/[Companion](index.md)/[createVersion](create-version.md)

# createVersion

[androidJvm]\
fun [createVersion](create-version.md)(versionName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Version](../index.md)

Semantic Versioning rule 를 따르는 [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) 버전 정보로 [Version](../index.md) create

#### Return

[Version](../index.md) 을 return. Semantic Versioning rule 를 따르지 않을 경우, Version(0,0,0,null) 을 return.

## Parameters

androidJvm

| | |
|---|---|
| versionName | Semantic Versioning rule 를 따르는 String |
