package com.kt.basickit.pollingcenter

/**
 * 폴링에 등록할 콜백 타입
 */
typealias OnPolling = suspend () -> Unit

/**
 * 수행할 폴링 작업에 대한 정보
 * 폴링 센터에 작업 추가 시 이용할 것
 *
 * @param key 폴링 작업 구분애 이용할 고유키
 * @param pollingType 폴링 작업 추가 시 바로 실행 할 지 여부
 * @param interval 폴링 작업 실행 주기
 * @param onPolling 수행할 폴링 작업
 */
data class WorkableItem(
    val key: String,
    val pollingType: PollingCenter.PollingType,
    val interval: Long,
    val onPolling: OnPolling,
) {
    /**
     * 디폴트 폴링 주기를 사용 하는 경우
     */
    companion object DefaultWorkableItem {
        fun usingDefaultPollingInterval(
            key: String,
            pollingType: PollingCenter.PollingType,
            onPolling: OnPolling,
        ): WorkableItem {
            return WorkableItem(
                key, pollingType, 5000, onPolling,
            )
        }
    }
}
