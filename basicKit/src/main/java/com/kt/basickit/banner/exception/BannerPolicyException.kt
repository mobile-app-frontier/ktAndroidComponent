package com.kt.basickit.banner.exception

import java.lang.Exception

/**
 * Banner 내부 로직에 의해 발생할 수 있는 Exception
 *
 * @constructor
 * error message 를 지닌 [Exception] 을 create.
 *
 * @param message error message(reason)
 */
sealed class BannerPolicyException(message: String): Exception(message) {
    /**
     * Invalid State
     * ex) BannerManager 를 initialize 전에 사용 했을 경우
     *
     * @constructor
     * error message 를 지닌 [BannerPolicyException] 를 create.
     *
     * @param message error message(reason)
     */
    class InvalidState(message: String): BannerPolicyException(message)

    /**
     * PopupBanner start 에 실패
     * ex) FragmentActivity 가 아닌 Activity 에서 BannerManager 를 사용 했을 경우
     *
     * @constructor
     * error message 를 지닌 [BannerPolicyException] 를 create.
     *
     * @param message error message(reason)
     */
    class FailToStartPopup(message: String): BannerPolicyException(message)
}