package com.kt.basickit.banner.exception

import java.lang.Exception

sealed class BannerPolicyException(message: String): Exception(message) {
    class InvalidState(message: String): BannerPolicyException(message)
    class FailToStartPopup(message: String): BannerPolicyException(message)
}