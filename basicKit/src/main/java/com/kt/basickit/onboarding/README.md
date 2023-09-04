# OnBoarding

OnBoarding은 몇 개의 스탭을 가지며 이전 또는 다음 스탭으로 이동하는 시나리오를 구현합니다.

각 스탭은 고유한 라우트와 화면을 가지고 구성 됩니다.
스탭 목록을 통해 온보딩 시나리오를 구현할 수 있습니다.

- [사용 방법 - Jetpack Compose](#how_to_use_with_compose)
- [사용 방법 - XML](#how_to_use_with_XML)
- [Structure](#structure)

## 사용 방법 - Jetpack Compose

Jetpack Compose로 온보딩을 구현하는 방법.

### Step 1

스탭에서 보여줄 화면을 구현 합니다.
각 화면에서 온보딩 내비게이션 컨트롤러 또는 온보딩 진행 관련 조작이 필요하다면 `NavHostController`, `OnBoardingViewModel`을 이용할 수 있습니다.

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

Step1에서 정의한 스탭 화면과 화면의 경로를 넣어 `OnBoardingRoute`를 구현 합니다.

``` Kotlin
enum class AppOnBoardingRoute(
    override val routeName: String,
    override val screen: @Composable (NavHostController, OnBoardingViewModel) -> Unit,
) : OnBoardingRoute {
    
    OnBoardingPageOne(
        "/onBoardingPageOne",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingStepOneScreen(onBoardingNavController, onBoardingController) },
    ),
    OnBoardingPageTwo(
        "/onBoardingPageTwo",
        screen = { onBoardingNavController, onBoardingController -> OnBoardingStepTwoScreen(onBoardingNavController, onBoardingController) },
    ),
    // ...
    ;
}
```

### Step 3

OnBoarding에서 보여 주고자 하는 스탭들을 파라미터로 넣어 `OnBoardingScreen`을 생성합니다.
`onBoardingTopArea`, `onBoardingBottomArea`에 OnBoarding 스탭 별 화면에 상관 없이 OnBoarding 내내 보여줄 뷰를 넣을 수
있습니다.

```kotlin
OnBoardingScreen(
    steps = AppOnBoardingRoute.values().toList(),
    // 상단에 뷰를 띄워주는 예시. 프로그래스 뷰 등을 띄울 수 있음.
    onBoardingTopArea = { onBoardingNavController, onBoardingViewModel ->
        ProgressView(onBoardingNavController, onBoardingViewModel)
    }
)
```

## 사용 방법 - XML

### Step 1

스탭에서 보여줄 화면을 구현 합니다.
각 화면에서 온보딩 내비게이션 컨트롤러 또는 온보딩 진행 관련 조작이 필요하다면 `NavHostController`, `OnBoardingViewModel`을 이용할 수 있습니다.

```xml
<!-- onboarding_page_one.xml Example -->
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable name="onboardingViewModel"
            type="com.kt.basickit.onboarding.OnBoardingViewModel" />
    </data>

    <!-- 보여줄 스탭 뷰 구현 -->
</layout>
```

### Step 2

Step1에서 지정한 XMl에 대한 뷰 바인딩 콜백과, 바인딩에 값을 지정하는 메서드를 화면의 경로와 함께 넣어 `XmlOnBoardingRoute`를 구성합니다.

```kotlin
OnBoardingPageOne(
    binding = OnboardingPageOneBinding::inflate, // onboarding_page_one.xml 에 대해 자동 생성된 뷰 바인딩
    updateBiding = { viewBinding, onBoardingViewModel ->
        viewBinding.onboardingViewModel = onBoardingViewModel
    }
)
```

### Step 3

OnBoarding에서 보여 주고자 하는 스탭들을 파라미터로 넣어 `OnBoardingScreen`을 생성합니다.
`onBoardingTopArea`, `onBoardingBottomArea`에 스탭 별 화면에 상관 없이 OnBoarding 내내 보여줄 뷰를 넣을 수
있습니다.

```kotlin
OnBoardingScreen<XmlOnBoardingRoute, XmlOnBoardingVerticalSideArea>(
    steps = listOf(
        OnBoardingPageOne(
            // ...
        ),
        onBoardingTopArea = object : XmlOnBoardingVerticalSideArea {
            // ...
        }
    )
)
```

### Step 4

`OnBoardingScreen`은 Composable 이므로, 해당 온보딩을 띄우고자 하는 XML에 `ComposeView`를 추가 합니다.

```xml

<androidx.compose.ui.platform.ComposeView android:id="@+id/composeView"/>
```

### Step 5

온보딩을 띄우려는 `ComposeView`에 `OnBoardingScreen`을 추가 합니다.

```kotlin
val composeView = view.findViewById<ComposeView>(R.id.composeView)
composeView.setContent {
    OnBoardingScreen(
        //...
    )
}
```

## Structure

### `OnBoardingScreen`

| name | Type | Description |
| :--- | :--: | ----------: |
| steps | List<OnBoardingRoute> | 사용자 정의 OnBoarding 스탭 리스트 |
| startStep | Int | 시작 스탭 인덱스 |
| onBoardingTopArea | OnBoardingVerticalSideArea? | 온보딩 상단 영역 표출 화 |
| onBoardingBottomArea | OnBoardingVerticalSideArea? | 온보딩 하단 영역 표출 화면 |
