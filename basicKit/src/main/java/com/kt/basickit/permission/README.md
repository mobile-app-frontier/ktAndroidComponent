
# PermissionManager

앱이 필요로 하는 권한을 실행중에 획득 할 수 있도록 돕는 kit으로 JetpackCompose에서 지원을 한다.

- [Example](#example)
- [XML version](#xmlactivityfragment-version)

## Example

1. 권한 요청

``` kotlin
 @Composable
 fun MainScreen() {
     RequestPermissionsEffect(permissoins) {
         // do something
         viewModel.fetching()
     }
 }
```


2. 권한 상태 체크

``` kotlin
 @Composable
 fun MainScreen() {
     val context = LocalContext.current
     val permissionState = currentPermissionState(context, Manifest.Permission.Camera)
     if (permissionState == PermissionState.Grant) {
         ValidScreen()
     } else {
         InvalidScreen()
     }
 }
```

## XML(Activity,Fragment) version

fragment나 activity를 기반으로 하는 앱에서는 PermissionKit을 사용 할 수 없다.

fragment나 activity 기반인 경우 다음과 같이 앱을 작성 하면, permission을 획득 할 수 있다.

``` kotlin
class PermissionActivity: AppCompatActivity() {

    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // do Something
            } else {
                //
                // do Something
            }
        }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        checkPermission()
    }
    
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this@PermissionActivity,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // do something
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}
```
