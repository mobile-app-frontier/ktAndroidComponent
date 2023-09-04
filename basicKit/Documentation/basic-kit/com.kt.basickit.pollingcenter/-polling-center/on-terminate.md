//[basicKit](../../../index.md)/[com.kt.basickit.pollingcenter](../index.md)/[PollingCenter](index.md)/[onTerminate](on-terminate.md)

# onTerminate

[androidJvm]\
fun [onTerminate](on-terminate.md)()

애플리케이션 종료 시 호출할 것. 호출 시 폴링 센터에 등록된 폴링 작업들을 모두 제거한다. 호출 시점에, 인터벌 동안 실행 되기를 기다리던 폴링 작업들을 취소한다.
