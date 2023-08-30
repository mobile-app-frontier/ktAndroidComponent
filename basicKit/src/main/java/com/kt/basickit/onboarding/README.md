# OnBoarding

OnBoarding은 몇 개의 스탭을 가지며 이전 또는 다음 스탭으로 이동하는 시나리오를 구현합니다.

각 스탭은 고유한 라우트와 보여줄 화면을 가지고 구성 됩니다.
스탭들의 리스트를 가지고 온보딩 시나리오를 구현할 수 있습니다.

## 사용 방법
### Step 1
온보딩에 이용할 스탭에서 보여줄 화면을 구현 합니다.
각 화면에서 온보딩 내비게이션 컨트롤러 또는 온보딩 진행 관련 조작이 필요하다면 [NavHostController], [OnBoardingViewModel]을 이용할 수 있습니다.

```Kotlin
@Composable
fun OnBoardingStepOneScreen(
    onBoardingNavController: NavHostController,
    onBoardingController: OnBoardingViewModel,
) {
    /// OnBoardingViewModel의 controllerState를 통해 온보딩 진행 상태를 바라볼 수 있음.
    val onBoardingControllerState =
        onBoardingController.controllerState.collectAsStateWithLifecycle()

    /// 현재 스탭이 온보딩의 첫번째 스탭이었다면, 이전 스탭으로의 이동은 온보딩 취소 호출.
    if (onBoardingControllerState.value is OnBoardingCancel) {
        // 온보딩 취소 시 수행할 내용 정의.
    }

    /// 시스템 백버튼 누르면 온보딩 내 이전 스탭으로 이동.
    BackHandler {
        onBoardingController.prev()
    }

    /// 예시 화면.
    Column { 
        Text("OnBoarding step 1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            /// 온보딩 내 다음 스탭으로 이동.
            onBoardingController.next()
        }) {
            Text("다음 단계", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }

}
```

### Step 2
이전에 정의한 스탭 별 화면과, 화면의 고유한 경로를 정의 하여 OnBoardingRoute 를 구현 합니다.
``` Kotlin
enum class AppOnBoardingRoute(
    override val routeName: String,
    override val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit,
) : OnBoardingRoute {
    
    OnBoardingPageOne(
        "/onBoardingPageOne",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingPageOneScreen(onBoardingNavController, onBoardingController) },
    ),
    OnBoardingPageTwo(
        "/onBoardingPageTwo",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingPageTwoScreen(onBoardingNavController, onBoardingController) },
    ),
    // ...
    ;
}
```

### Step 3
OnBoarding에서 보여 주고자 하는 스탭들을 파라미터로 넣어 OnBoardingScreen을 생성 합니다.
이 때, OnBoarding 스탭 별 화면에 상관 없이 OnBoarding 내내 보여줄 뷰를 넣을 수 있습니다.
```kotlin
OnBoardingScreen(
    steps = AppOnBoardingRoute.values().toList(),
    onBoardingTopArea = { onBoardingNavController, onBoardingViewModel ->
        ProgressView(onBoardingNavController, onBoardingViewModel)
    }
)
```

- [Structure](#structure)

## Structure
### `OnBoardingScreen`
| name | Type | Description |
| :--- | :--: | ----------: |
| steps | List<OnBoardingRoute> | 사용자 정의 OnBoarding 스탭 리스트 |
| startStep | Int | 시작 스탭 인덱스 |
| onBoardingTopArea | (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? | 온보딩 상단 영역 표출 화 |
| onPolling | (@Composable (NavHostController, OnBoardingViewModel) -> Unit)? | 온보딩 하단 영역 표출 화면 |
