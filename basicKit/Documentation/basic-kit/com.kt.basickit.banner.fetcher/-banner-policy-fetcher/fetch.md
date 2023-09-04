//[basicKit](../../../index.md)/[com.kt.basickit.banner.fetcher](../index.md)/[BannerPolicyFetcher](index.md)/[fetch](fetch.md)

# fetch

[androidJvm]\
suspend fun [fetch](fetch.md)()

remoteBannerPolicy 와 localBannerPolicy 를 동시에 fetch 하고 비교 하여 보여줄 배너를 filtering 함.

- 
   remoteBannerPolicy 와 localBannerPolicy 를 fetch 성공 하면 [state](state.md) 는 [BannerPolicyState.Fetched](../-banner-policy-state/-fetched/index.md) 상태로 변함.
- 
   remoteBannerPolicy 와 localBannerPolicy 를 비교 하여 filtering 한 후, 유효한 로컬 배너 정책을 update &[state](state.md) 는 [BannerPolicyState.Success](../-banner-policy-state/-success/index.md) 상태로 변함.
- 
   fetch 과정 중에 [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) 이 발생할 경우, 내부 에서 try-catch 로 처리. [state](state.md) 는 [BannerPolicyState.Fail](../-banner-policy-state/-fail/index.md) 상태로 변함.
