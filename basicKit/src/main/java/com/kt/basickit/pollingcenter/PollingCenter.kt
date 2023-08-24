package com.kt.basickit.pollingcenter

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
private data class RunnableWorkableItem constructor(val workableItem: WorkableItem, val runnable: Runnable)

object PollingCenter {

    private const val TAG = "PollingCenter"
    private const val HANDLER_NAME = "Custom handler thread"
    private var backgroundHandler: Handler? = null

    enum class PollingType {
        Instant,
        Delayed,
    }

    private val runnableMap = mutableMapOf<String, RunnableWorkableItem>()

    fun setPollingCenter() {
        val thread = HandlerThread(HANDLER_NAME)
        thread.start()
        backgroundHandler = Handler(thread.looper)
    }

    fun addTask(workable: WorkableItem) {
        if(runnableMap.containsKey(workable.key)) return
        val task = makePollingRunnable(workable.onPolling, workable.interval!!)
        runnableMap[workable.key] = RunnableWorkableItem(workable, task)
        postTask(task, workable)
    }

    private fun makePollingRunnable(onPolling: OnPolling, interval: Long) : Runnable {
        return object: Runnable {
            override fun run() {
                runBlocking {
                    onPolling.invoke()
                }
                backgroundHandler?.postDelayed(this, interval)
            }
        }
    }

    private fun postTask(task: Runnable, workable: WorkableItem) {
        when(workable.pollingType) {
            PollingType.Instant -> {
                backgroundHandler?.post(task)
            }
            PollingType.Delayed -> {
                backgroundHandler?.postDelayed(task, workable.interval!!)
            }
        }
    }

    fun resume() {
        val isThreadAlive = backgroundHandler?.looper?.thread?.isAlive ?: false
        if(isThreadAlive) {
            cancel()
        }
        setPollingCenter()
        runnableMap.forEach { pollingItem ->
            postTask(pollingItem.value.runnable, pollingItem.value.workableItem)
        }
    }

    /**
     * 작업 키를 넣어 폴링 큐에서 삭제
     */
    fun removeTask(key: String) {
        if (runnableMap.containsKey(key)) {
            val task = runnableMap[key]!!
            backgroundHandler?.removeCallbacks(task.runnable)
            runnableMap.remove(key)
        }
    }

    private fun cancel() {
        backgroundHandler?.looper?.quitSafely()
        backgroundHandler = null
    }

    /**
     * main destroy 시 호출할 것
     */
    fun destroy() {
        Log.d(TAG, "destroyed")
        backgroundHandler?.looper?.quitSafely()
        backgroundHandler = null
    }
}
