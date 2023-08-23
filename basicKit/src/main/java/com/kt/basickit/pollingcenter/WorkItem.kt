package com.kt.basickit.pollingcenter

sealed interface WorkItem {
    val functions: List<OnPolling>
}

interface LazyWorkItem: WorkItem

interface ImmediateWorkItem: WorkItem
