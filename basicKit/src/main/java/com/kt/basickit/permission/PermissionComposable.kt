package com.kt.basickit.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext


/**
 * Remember permission launcher state
 *
 * @param result
 * @receiver
 */
@Composable
fun rememberPermissionLauncher (result: (Boolean)-> Unit) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission(),
    onResult = result
)

/**
 * Request permission side effect
 *
 * @param permission
 * @param result
 * @receiver
 */
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
 * Remember permissions launcher
 *
 * @param result
 * @receiver
 */
@Composable
fun rememberPermissionsLauncher (result: (Map<String,Boolean>)-> Unit) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions(),
    onResult = result
)

/**
 * Request permissions effect
 * ``` kotlin
 *  @Composable
 *  fun MainScreen() {
 *      RequestPermissionsEffect(permissoins) {
 *          // do something
 *          viewModel.fetching()
 *      }
 *  }
 * ```
 * @param permissions
 * @param result
 * @receiver
 */
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



