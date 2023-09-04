package com.kt.basickit.permission

import android.Manifest
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

/**
 * Current permission state
 *
 * ``` kotlin
 *  @Composable
 *  fun MainScreen() {
 *      val context = LocalContext.current
 *
 *      val permissionState = currentPermissionState(context, Manifest.Permission.Camera)
 *
 *      if (permissionState == PermissionState.Grant) {
 *          ValidScreen()
 *      } else {
 *          InvalidScreen()
 *      }
 *  }
 * ```
 *
 * @param context
 * @param permission
 * @return
 */
fun currentPermissionState(context: Context, permission: String): PermissionState {
    return PermissionState.fromInt(
        ContextCompat.checkSelfPermission(
        context,
        permission
    ))
}

/**
 * Current permissions state
 *
 * ``` kotlin
 *  @Composable
 *  fun MainScreen() {
 *      val context = LocalContext.current
 *      val stateMap = currentPermissionsState(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
 *          Manifest.permission.ACCESS_COARSE_LOCATION))
 *
 *      if (stateMap.values.all { it == PermissionState.Grant }) {
 *          ValidScreen()
 *      } else {
 *          InvalidScreen()
 *      }
 *  }
 * ```
 * @param permissions
 * @param context
 * @return
 */

fun currentPermissionsState(context: Context, permissions: Array<String>): Map <String, PermissionState>{
    return permissions.associateWith {
        currentPermissionState(context, it)
    }
}