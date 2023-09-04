//[basicKit](../../index.md)/[com.kt.basickit.permission](index.md)/[currentPermissionsState](current-permissions-state.md)

# currentPermissionsState

[androidJvm]\
fun [currentPermissionsState](current-permissions-state.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), permissions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [PermissionState](-permission-state/index.md)&gt;

Current permissions state

@Composable\
fun MainScreen() {\
    val context = LocalContext.current\
    val stateMap = currentPermissionsState(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,\
        Manifest.permission.ACCESS_COARSE_LOCATION))\
\
    if (stateMap.values.all { it == PermissionState.Grant }) {\
        ValidScreen()\
    } else {\
        InvalidScreen()\
    }\
}

#### Return

## Parameters

androidJvm

| | |
|---|---|
| permissions |  |
| context |  |
