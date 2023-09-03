//[basicKit](../../index.md)/[com.kt.basickit.permission](index.md)/[RequestPermissionsEffect](-request-permissions-effect.md)

# RequestPermissionsEffect

[androidJvm]\

@Composable

fun [RequestPermissionsEffect](-request-permissions-effect.md)(permissions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, result: ([Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [PermissionState](-permission-state/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Request permissions effect

@Composable\
fun MainScreen() {\
    RequestPermissionsEffect(permissoins) {\
        // do something\
        viewModel.fetching()\
    }\
}

#### Receiver

## Parameters

androidJvm

| | |
|---|---|
| permissions |  |
| result |  |
