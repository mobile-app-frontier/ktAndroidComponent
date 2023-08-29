# PollingCenter

폴링할 작업을 등록 하면, 특정 주기 마다 작업을 호출 하고 실행 합니다.

### `폴링 센터 시작하기`
폴링 센터는 사용자가 직접 `cancel`을 호출 하여 동작을 중지 하거나, `onTerminate`를 호출 하지 않는 한 앱 런타임 내내 동작 하는 싱글톤 객체 입니다.
적절한 곳에서 `initializePollingCenter`를 호출 하여 폴링 센터를 초기화 한 후 사용 합니다.

### `폴링 센터에 폴링할 작업 추가하기`
`WorkableItem`은 폴링 센터에 추가할 작업 정보를 담고 있는 객체 입니다.
추가할 폴링 작업에 대한 정보를 넣어 `addTask`로 폴링 센터에 작업을 추가할 수 있습니다.

### `폴링 센터에서 작업 삭제하기`
폴링 센터에서 작업을 삭제하려면 `removeTask`에 삭제하고자 하는 작업의 키를 넣어 호출 합니다.
폴링 센터에 등록된 모든 작업을 삭제하려면 `removeAllTasks`를 호출 합니다.

### `폴링 센터 멈추고 재개하기`
실행 중인 폴링 센터를 중지 하기 위해 `cancel`을 호출할 수 있고, 재개하려면 `resume`을 호출 합니다.
중지 해도 이전에 등록 되었던 작업들의 정보는 삭제 되지 않습니다.
재개 하면 이전에 폴링 센터에 등록 되어 있던 작업들이 각자 정보에 맞게 재개 됩니다.

### `폴링 센터 끝내기`
앱 종료 시 `onTerminate`를 호출 하여 폴링 센터를 종료 시킵니다.

- [Example](#example)
- [Structure](#structure)

## Example
``` Kotlin
class MainActivity :
    ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePollingCenter() /// 폴링 센터 초기화
        setContent {
            NavtesterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PollingSample()
                }
            }
        }
    }
}

@Composable
fun PollingSample() {

    val TAG = "PollingCenter"
    val instantWithIntervalTwoSec = "instantWithIntervalTwoSec"

    LaunchedEffect(Unit) {
        PollingCenter.addTask(
            WorkableItem(instantWithIntervalTwoSec, PollingCenter.PollingType.Instant, 2000)
            { Log.d(TAG, "task (instant, interval 2sec) running") }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            runBlocking {
                PollingCenter.removeTask(instantWithIntervalTwoSec)
            }
        }
    }
}
```

## Structure
`WorkableItem`
| name | Type | Description |
| :--- | :---: | ---: | --- |
| key | String | 폴링 작업의 고유키 |
| pollingType | PollingCenter.PollingType | 업폴링 작업 추가 시 바로 실행 할 지 여부 |
| interval | Long | 폴링 작업 실행 주기 |
| onPolling | OnPolling | 수행할 폴링 작업 |
