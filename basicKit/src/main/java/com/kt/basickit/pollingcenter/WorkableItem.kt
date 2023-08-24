package com.kt.basickit.pollingcenter

/**
 * 폴링에 등록할 콜백 타입
 */
typealias OnPolling = suspend () -> Unit

private const val defaultPollingInterval: Long = 5000 // ms

/**
 * 폴링 센터에 작업 추가 시 이용할 것
 */
data class WorkableItem(
    val key: String,
    val pollingType: PollingCenter.PollingType,
    val interval: Long? = defaultPollingInterval,
    val onPolling: OnPolling,
)
