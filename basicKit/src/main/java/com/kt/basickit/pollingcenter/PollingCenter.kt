package com.kt.basickit.pollingcenter

import android.app.Application
import android.util.Log
import kotlinx.coroutines.runBlocking
import java.util.Timer
import kotlin.concurrent.timerTask

typealias OnPolling = suspend () -> Unit

object PollingCenter {

    private const val TIMER_INTERVAL = 5000
    private var timer: Timer? = null
    val workItems: MutableList<WorkItem> = mutableListOf()

    fun init(application: Application) {
        Log.d("polling center", "new polling center created")
        PollingController.init(application)
    }

    fun addWorkItem(item: WorkItem) {
        Log.d("polling center", "added $item!")
        if (workItems.checkDuplicate(item)) {
            Log.d("polling center", "added skipped cause already exists!")
            return
        }
        workItems.add(item)
        invokeWorkItems()
        if (item is ImmediateWorkItem) {
            runBlocking {
                item.functions.forEach {
                    it()
                }
            }
        }
    }

    private fun MutableList<WorkItem>.checkDuplicate(item: WorkItem): Boolean {
        return this.any { it::class == item::class }
    }

    private fun invokeWorkItems() {
        timer?.let {
            return
        }
        startTimer()
    }

    private fun startTimer() {
        cancelTimer()
        timer = Timer()
        val task = timerTask {
            runBlocking {
                runPolling()
            }
        }
        timer?.scheduleAtFixedRate(task, TIMER_INTERVAL.toLong(), TIMER_INTERVAL.toLong())
    }

    fun cancelTimer() {
        Log.d("polling center", "if timer exists, canceled!")
        timer?.cancel()
        timer = null
    }

    fun resumeTimer() {
        Log.d("polling center", "timer resumed!")
        startTimer()
    }

    private suspend fun runPolling() {
        Log.d("polling center", "called run!")
        workItems.forEach {
            it.functions.forEach {
                it()
            }
        }
    }

    inline fun <reified T> removeWorkItem() {
        workItems.filter { it is T }.forEach { item -> workItems.remove(item) }
    }
}
