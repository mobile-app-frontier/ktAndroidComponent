package com.kt.basickit.pollingcenter

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap

private data class RunnableWorkableItem constructor(val workableItem: WorkableItem, val runnable: Runnable)

object PollingCenter {

    private const val TAG = "PollingCenter"
    private const val HANDLER_NAME = "Custom handler thread"

    private val runnableMapMutex = Mutex()
    private var backgroundHandler: Handler? = null
    private var thread: HandlerThread? = null
    private val runnableMap = ConcurrentHashMap<String, RunnableWorkableItem>()

    /**
     * 폴링 센터에 추가한 작업이 추가 즉시 실행 되는지, 딜레이 후 실행 되는지 결정.
     */
    enum class PollingType {
        /**
         * 폴링 센터에 추가 하는 즉시 한번 작업을 실행한 후, 정해진 인터벌 대로 폴링 한다.
         */
        Instant,

        /**
         * 폴링 센터에 추가 하면, 정해진 인터벌 대로 폴링 한다.
         */
        Delayed,
    }

    /**
     * 폴링 센터 초기화 시 사용.
     * 사용자가 직접 cancel을 호출 하지 않는 한, 어플리케이션이 실행 되는 동안 동일한 스레드에서 폴링이 실행된다.
     */
    fun initializePollingCenter() {
        if (thread == null) {
            thread = HandlerThread(HANDLER_NAME).apply {
                start()
                backgroundHandler = Handler(this.looper)
            }
        }
    }

    /**
     * 폴링 작업을 추가할 때 사용.
     *
     * @param workable: 추가할 폴링 작업 정보를 담은 [WorkableItem]
     */
    suspend fun addTask(workable: WorkableItem) {
        runnableMapMutex.withLock {
            if (runnableMap.containsKey(workable.key)) return
            val task = makePollingRunnable(workable.onPolling, workable.interval)
            runnableMap[workable.key] = RunnableWorkableItem(workable, task)
            postTask(task, workable)
        }
    }

    /**
     * 추가된 폴링 작업의 정보를 가지고 폴링 센터에서 실행 가능한 형태로 변환.
     */
    private fun makePollingRunnable(onPolling: OnPolling, interval: Long): Runnable {
        return object : Runnable {
            override fun run() {
                CoroutineScope(Dispatchers.Default).launch {
                    onPolling.invoke()
                }
                backgroundHandler?.postDelayed(this, interval)
            }
        }
    }

    /**
     * 폴링 작업의 타입에 맞게 실행 방식을 결정 한다.
     * 예를 들어 Delay 라면, 추가 되었을 때 작업을 interval 후에 호출 하도록 한다.
     */
    private fun postTask(task: Runnable, workable: WorkableItem) {
        when (workable.pollingType) {
            PollingType.Instant -> {
                backgroundHandler?.post(task)
            }

            PollingType.Delayed -> {
                backgroundHandler?.postDelayed(task, workable.interval)
            }
        }
    }

    /**
     * 폴링 센터의 cancel을 호출 하여 폴링을 중단한 경우, 이 메서드를 호출하여 다시 시작할 수 있음.
     * 센터에 등록 되어 있던 폴링 작업들을 각 폴링 조건에 맞게 재개 한다.
     */
    fun resume() {
        val isThreadAlive = backgroundHandler?.looper?.thread?.isAlive ?: false
        if(isThreadAlive) {
            cancel()
        }
        initializePollingCenter()
        runnableMap.forEach { pollingItem ->
            postTask(pollingItem.value.runnable, pollingItem.value.workableItem)
        }
    }

    /**
     * 폴링 작업의 키를 넣어 폴링 센터에서 삭제.
     *
     * @param key: 삭제할 폴링 작업의 키
     */
    suspend fun removeTask(key: String) {
        runnableMapMutex.withLock {
            if (runnableMap.containsKey(key)) {
                val task = runnableMap[key]!!
                backgroundHandler?.removeCallbacks(task.runnable)
                runnableMap.remove(key)
            }
        }
    }

    /**
     * 폴링 센터에 등록된 모든 작업 삭제
     */
    suspend fun removeAllTasks() {
        runnableMapMutex.withLock {
            runnableMap.clear()
        }
    }

    /**
     * 폴링 센터 동작을 중단 하고 싶을 때 사용.
     * 호출 돼도 폴링 센터에 등록된 폴링 작업들은 제거 되지 않는다.
     * 호출 시점에, 인터벌 동안 실행 되기를 기다리던 폴링 작업들을 취소한다.
     */
    fun cancel() {
        // quitSafely: postDelay 사용 하여 실행 시점이 뒤로 밀린 메시지들은 실행 하고, quitSafely 호출 시점 보다 TimeStamp 가 뒤인 메시지들은 큐에서 제거한다.
        // quit: quit 호출 시점에 큐에 있는 메시지를 모두 제거 한다.
        Log.d(TAG, "canceled")
        thread?.quit()
        thread = null
        backgroundHandler = null
    }

    /**
     * 애플리케이션 종료 시 호출할 것.
     * 호출 시 폴링 센터에 등록된 폴링 작업들을 모두 제거한다.
     * 호출 시점에, 인터벌 동안 실행 되기를 기다리던 폴링 작업들을 취소한다.
     */
    fun onTerminate() {
        Log.d(TAG, "terminated")
        thread?.quit()
        thread = null
        backgroundHandler = null
        runBlocking {
            removeAllTasks()
        }
    }
}
