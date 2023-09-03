//[basicKit](../../index.md)/[com.kt.basickit.permission](index.md)

# Package com.kt.basickit.permission

## Types

| Name | Summary |
|---|---|
| [PermissionState](-permission-state/index.md) | [androidJvm]<br>enum [PermissionState](-permission-state/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[PermissionState](-permission-state/index.md)&gt; <br>Permission state |

## Functions

| Name | Summary |
|---|---|
| [currentPermissionsState](current-permissions-state.md) | [androidJvm]<br>fun [currentPermissionsState](current-permissions-state.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), permissions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [PermissionState](-permission-state/index.md)&gt;<br>Current permissions state |
| [currentPermissionState](current-permission-state.md) | [androidJvm]<br>fun [currentPermissionState](current-permission-state.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), permission: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PermissionState](-permission-state/index.md)<br>Current permission state |
| [rememberPermissionLauncher](remember-permission-launcher.md) | [androidJvm]<br>@Composable<br>fun [rememberPermissionLauncher](remember-permission-launcher.md)(result: ([Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): &lt;ERROR CLASS&gt;<br>Remember permission launcher state |
| [rememberPermissionsLauncher](remember-permissions-launcher.md) | [androidJvm]<br>@Composable<br>fun [rememberPermissionsLauncher](remember-permissions-launcher.md)(result: ([Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): &lt;ERROR CLASS&gt;<br>Remember permissions launcher |
| [RequestPermissionEffect](-request-permission-effect.md) | [androidJvm]<br>@Composable<br>fun [RequestPermissionEffect](-request-permission-effect.md)(permission: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), result: ([PermissionState](-permission-state/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Request permission side effect |
| [RequestPermissionsEffect](-request-permissions-effect.md) | [androidJvm]<br>@Composable<br>fun [RequestPermissionsEffect](-request-permissions-effect.md)(permissions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, result: ([Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [PermissionState](-permission-state/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Request permissions effect |
