# Banner

서버에서 받아온 배너 정책에 따라서 `PopupBanner` 혹은 `DefualBanner` 을 보여줌. 
또한 `landingType` 이 포함된 `Banner` 의 경우, 사용자 interaction(tap) 에 따라 `landing` 기능을 제공할 수 있도록 `landingPublisher` 을 제공 해줌. 
실제 landing 하는 것을 지원 하지는 않음.

### DefaultBanner
category 에 따라 분류 되며, `BannerManager` 를 통해 category 별 `ImageSlider` 형태의 `View` 를 제공 해줌.

### PopupBanner
`BannerManager`를 start 하면 `Sheet` 형식의 `PopupBanner` 를 순서 대로 표출 해줌. 

'다시 보지 않기', '일주일 동안 보지 않기', '하루 동안 보지 않기' 등의 기능을 제공 하며, 해당 기능은 단말에 `[String : Int]` 형식의 `LocalBannerPolicy` 로 저장 하여 
보여주지 않을 배너 정책으로 관리함. 


- [제약사항](#제약사항)
- [Example](#example)
- [structure](#structure)
- [MORE](#more)

## 제약사항
- `DefaultBanner` 와 `PopupBanner` 는 모두 우선 순위가 높은 것부터 표출됨.
- `DefaultBanner` 의 경우 같은 category 의 배너 들은 가로 세로 비율이 일정한 이미지 여야 함.
    ex) message category 의 배너 image 들의 가로 세로 비율은 모두 5 : 2
- `PopupBanner` 에 inApp 랜딩이 있을 경우, 반드시 가장 낮은 우선 순위를 가져야 함. inApp 랜딩을 포함한 `PopupBanner` 의 경우, 사용자 interaction 에 의해 
  랜딩이 실행 되었을 때 다음 popup banner 는 모두 보여 주지 않음.
- `BannerManager` 의 모든 기능은 `BannerPolicyFetcher` 가 fetch 에 성공한 이후(`BannerPolicyFetcher` 의 state 가 `BannerPolicyState.Success`) 에 사용 가능. 이전에 사용할 경우, `BannerPolicyException.InvalidState` Exception 이 발생함.
- `BannerManager` 의 `startPopupBanner` function 은 반드시 `FragmentActivity` 에서 호출 되어야 함. 그 외 (ex. `ComponentActivity`) 
  에서 호출 할 경우 `BannerPolicyException.FailToStartPopup` Exception 이 발생함.

## Example
### Step1. implement `BannerPolicyDataSource`

```kotlin
class BannerPolicyDataSourceImpl: BannerPolicyDataSource {
    val networkProvider = NetworkProvider() // your network provider ex) use Retrofit
    override suspend fun getBannerPolicy(): List<BannerPolicyItemModel> {
        return networkProvider.request() // request to your api server
    }
}

```

### Step2. create and fetch `BannerPolicyFetcher`
- create BannerPolicyFetcher

``` kotlin
val bannerPolicyFetcher = BannerPolicyFetcher(
    context = context,
    dataSource = BannerPolicyDataSourceImpl(),
    // read from your data store
    localBannerPolicyGetter = { return@BannerPolicyFetcher readLocalBannerPolicy() },
    // write to your data store
    localBannerPolicySetter = { saveLocalBannerPolicy(localBannerPolicy = it) },
    appVersion = BuildConfig.VERSION_NAME, // your app version
)
```

- fetch BannerPolicy

``` kotlin
bannerPolicyFetcher.fetch()
```

- you can observe BannerPolicyFetcher's State and handle this

``` kotlin
bannerPolicyFetcher.state.collect {
    when (it) {
        is BannerPolicyState.Success -> // do something
        is BannerPolicyState.Fail -> // do something
        else -> {
            // do something
        }
    }
}

```

### Step 3. start `PopupBanner` or build `DefaultBanner` View from BannerManager or observe landingPublisher
- start PopupBanner

```kotlin
BannerManager.startPopupBanner(context)
```

- build DefaultBannerView
```kotlin
// get Fragment for category
BannerManager.getDefaultBanners(category)
```
or
```xml
 <!--use xml layout-->
 <androidx.fragment.app.FragmentContainerView
 android:id="@+id/default_banner"
 android:name="com.kt.basickit.banner.view.defaultBanner.DefaultBannerFragment"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 app:category="category"/>
```

- observe landingType
```kotlin
BannerManager.landingType.collect {
    when (it) {
        is BannerLandingType.None -> // do nothing. never called.
        is BannerLandingType.InApp -> // land to destinaiton
        is BannerLandingType.Web -> // land to web
    }  
}
```

## Structure

### BannerPolicyItemModel
| name | type | required / optional | Description |
| :--- | :---:| :-----------------: | ----------: |
| id | String | required | 배너의 ID | 
| priority | Int | required | 배너의 우선순위 |
| contentType | String | required | [contentType](#contenttype) | 
| content | String | required | [content](#content) |
| landingType | String? | optional | [landingType](#landingtype) | 
| landingDestination | String? | optional | landingType 이 "web" 일 경우, web 의 url. |
| closeType | String? | optional | [closeType](#closetype) | 
| appVersion | String? | optional | 보여줄 target app version. 없을 경우 모든 version 에서 보여줌. | 
| category | String? | optional | `DefaultBanner` 일 경우에만 유효. `DefaultBanner` 의 카테고리 | 
| type | String | required | [type](#type) | 
| additionalInfo | [String : String]? | optional | extra format | 

#### contentType
- DefaultBanner
    - "I": image. Image 형식의 content. 모두에게 open 되어 있는 공개 이미지 url 를 통해서만 가능.
- PopupBanner
    - "I": Image 형식의 content 를 보낼때 사용. 모두에게 open 되어 있는 공개 이미지 url 를 통해서만 가능.
    - "T": 텍스트 형식의 content 를 보낼때 사용
    - "H": Html 형식의 content 를 보낼때 사용. styled text 를 보내기 원할 때 사용 권장.

#### content
contentType 에 따라서 달라짐
- "I": 보여줄 이미지의 url.
    - Image 가 깨지는 것을 방지하기 위해. 주어진 이미지 비율을 유지하며 보여줌. `DefaultBanner` 의 경우 같은
    category 의 Image 들은 모두 같은 비율을 유지하도록 권장. `PopupBanner` 의 경우 단말의 가로 사이즈에
    맞추어 세로 길이를 수정하여 보여줌.(비율 유지) 따라서 이미지가 잘려서 보여질 수 있음.
- "T": content 영역에 보여주고 싶은 문자열
- "H": HTML 형식의 String. HTML String 자체에 링크를 거는 것은 권장하지 않음.

#### landingType
- "none" or "" : landing 되지 않음.
- "web": web view landing.
- All the rest: inapp landing.

#### closeType
`DefaultBanner` 일 경우에만 지원.
- "close": 닫기. 한번 보여주고 다시 보여주지 않음.
- "never": 다시 보지 않기.
- "week": 일주일 동안 보지 않기.
- "today": 오늘은 보지 않기.

#### type
- "default": Image Slider 배너
- "popup" : Sheet 배너

두 가지 문자열 에 해당 하지 않는 경우, 해당 배너는 사용 하지 않음. 반드시 두 가지 중 하나 여야만 함.