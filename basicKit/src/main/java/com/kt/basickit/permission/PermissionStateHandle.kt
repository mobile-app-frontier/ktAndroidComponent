package com.kt.basickit.permission

import android.content.Context
import androidx.core.content.ContextCompat

/// check single permission state
fun currentPermissionState(context: Context, permission: String): PermissionState {
    return PermissionState.fromInt(
        ContextCompat.checkSelfPermission(
        context,
        permission
    ))
}

/// check permissions state
fun currentPermissionsState(permissions: Array<String>, context: Context): Map <String, PermissionState>{
    return permissions.associateWith {
        currentPermissionState(context, it)
    }
}