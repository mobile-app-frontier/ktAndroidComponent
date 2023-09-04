//[basicKit](../../index.md)/[com.kt.basickit.permission](index.md)/[currentPermissionState](current-permission-state.md)

# currentPermissionState

[androidJvm]\
fun [currentPermissionState](current-permission-state.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), permission: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PermissionState](-permission-state/index.md)

Current permission state

@Composable\
fun MainScreen() {\
    val context = LocalContext.current\
\
    val permissionState = currentPermissionState(context, Manifest.Permission.Camera)\
\
    if (permissionState == PermissionState.Grant) {\
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
| context |  |
| permission |  |
