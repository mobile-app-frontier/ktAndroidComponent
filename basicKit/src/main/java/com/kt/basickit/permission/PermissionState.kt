package com.kt.basickit.permission

import android.content.pm.PackageManager


/**
 * Permission state
 *
 * Grant: 사용자로 부터 동의를 얻은 상태.
 * Denied: 사용자가 허용을 거부 한 상태.
 * @constructor Create empty Permission state
 */
enum class PermissionState {
    Grant,
    Denied;

    companion object {
        fun fromInt(state: Int): PermissionState {
            return when(state) {
                PackageManager.PERMISSION_GRANTED -> {
                    Grant
                }

                PackageManager.PERMISSION_DENIED -> {
                    Denied
                }

                else -> {
                    Denied
                }
            }
        }

        fun fromBoolean(state: Boolean): PermissionState {
            return when(state) {
                true -> {
                    Grant
                }
                false -> {
                    Denied
                }
            }
        }
    }
}