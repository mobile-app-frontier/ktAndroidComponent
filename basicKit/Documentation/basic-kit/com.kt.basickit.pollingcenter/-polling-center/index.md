//[basicKit](../../../index.md)/[com.kt.basickit.pollingcenter](../index.md)/[PollingCenter](index.md)

# PollingCenter

[androidJvm]\
object [PollingCenter](index.md)

## Types

| Name | Summary |
|---|---|
| [PollingType](-polling-type/index.md) | [androidJvm]<br>enum [PollingType](-polling-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[PollingCenter.PollingType](-polling-type/index.md)&gt; <br>폴링 센터에 추가한 작업이 추가 즉시 실행 되는지, 딜레이 후 실행 되는지 결정. |

## Functions

| Name | Summary |
|---|---|
| [addTask](add-task.md) | [androidJvm]<br>suspend fun [addTask](add-task.md)(workable: [WorkableItem](../-workable-item/index.md))<br>폴링 작업을 추가할 때 사용. |
| [cancel](cancel.md) | [androidJvm]<br>fun [cancel](cancel.md)()<br>폴링 센터 동작을 중단 하고 싶을 때 사용. 호출 돼도 폴링 센터에 등록된 폴링 작업들은 제거 되지 않는다. 호출 시점에, 인터벌 동안 실행 되기를 기다리던 폴링 작업들을 취소한다. |
| [initializePollingCenter](initialize-polling-center.md) | [androidJvm]<br>fun [initializePollingCenter](initialize-polling-center.md)()<br>폴링 센터 초기화 시 사용. 사용자가 직접 cancel을 호출 하지 않는 한, 어플리케이션이 실행 되는 동안 동일한 스레드에서 폴링이 실행된다. |
| [onTerminate](on-terminate.md) | [androidJvm]<br>fun [onTerminate](on-terminate.md)()<br>애플리케이션 종료 시 호출할 것. 호출 시 폴링 센터에 등록된 폴링 작업들을 모두 제거한다. 호출 시점에, 인터벌 동안 실행 되기를 기다리던 폴링 작업들을 취소한다. |
| [removeAllTasks](remove-all-tasks.md) | [androidJvm]<br>suspend fun [removeAllTasks](remove-all-tasks.md)()<br>폴링 센터에 등록된 모든 작업 삭제 |
| [removeTask](remove-task.md) | [androidJvm]<br>suspend fun [removeTask](remove-task.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>폴링 작업의 키를 넣어 폴링 센터에서 삭제. |
| [resume](resume.md) | [androidJvm]<br>fun [resume](resume.md)()<br>폴링 센터의 cancel을 호출 하여 폴링을 중단한 경우, 이 메서드를 호출하여 다시 시작할 수 있음. 센터에 등록 되어 있던 폴링 작업들을 각 폴링 조건에 맞게 재개 한다. |
