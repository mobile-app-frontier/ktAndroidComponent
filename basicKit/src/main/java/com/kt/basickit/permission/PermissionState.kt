package com.kt.basickit.permission

import android.content.pm.PackageManager


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