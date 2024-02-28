package org.example.project.utils

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import commons.domain.Callback
import kotlin.Exception

object PermissionUtils {
    fun requestDevicePermission(context: Context, permission: String){
        ContextCompat.checkSelfPermission(context, permission)
    }

    fun requestPermissionLauncher(act: ComponentActivity, permissionCallback: Callback<String>): ActivityResultLauncher<String> {
        return act.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                permissionCallback.onSuccess("permission granted")
            } else {
                permissionCallback.onError(Exception("permission grant process has failed"))
            }
        }
    }
}