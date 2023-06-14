package com.kt.basickit.permission

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

/**
 * Launcher Composable
 */

@Composable
fun rememberPermissionLauncher (result: (Boolean)-> Unit) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission(),
    onResult = result
)

@Composable
fun RequestPermissionEffect (permission: String, result:(PermissionState)-> Unit) {
    val launcher = rememberPermissionLauncher {
        when(it) {
            true -> result(PermissionState.Grant)
            false -> result(PermissionState.Denied)
        }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = "", block = {
        // if permission is granted then
        // result call
        // else
        // launch permission request
        currentPermissionState(context, permission).run {
            if (this == PermissionState.Grant) {
                result(this)
            } else {
                launcher.launch(permission)
            }
        }
    })
}

/**
 * request Permission list
 */

@Composable
fun rememberPermissionsLauncher (result: (Map<String,Boolean>)-> Unit) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions(),
    onResult = result
)

@Composable
fun RequestPermissionsEffect (permissions: Array<String>, result:(Map<String, PermissionState>)-> Unit) {
    val launcher = rememberPermissionsLauncher {
        result(it.map {entry ->
            entry.key to PermissionState.fromBoolean(entry.value)
        }.toMap())
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = "", block = {
        // if permission is granted then
        // result call
        // else
        // launch permission request
        if (permissions.all {
                currentPermissionState(context, it) == PermissionState.Grant
        }){
            result(permissions.associateWith {
                PermissionState.Grant
            })
        } else {
            launcher.launch(permissions)
        }
    })
}



