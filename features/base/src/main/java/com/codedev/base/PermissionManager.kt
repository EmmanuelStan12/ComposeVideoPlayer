package com.codedev.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

object PermissionManager {

    val permissions = mutableMapOf<String, String>(
        Manifest.permission.CAMERA to "Camera is needed",
        Manifest.permission.WRITE_EXTERNAL_STORAGE to "write external storage",
        Manifest.permission.READ_EXTERNAL_STORAGE to "read external storage",
    ).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            put(Manifest.permission.READ_MEDIA_AUDIO, "read media audio")
            put(Manifest.permission.READ_MEDIA_VIDEO, "read media video")
        }
    }

    val CAMERA_PERMISSION: Pair<String, String> = Pair(Manifest.permission.CAMERA, "Camera is needed")
    val READ_EXTERNAL_STORAGE_PERMISSION: Pair<String, String> = Pair(Manifest.permission.READ_EXTERNAL_STORAGE, "Camera is needed")
    val READ_MEDIA_AUDIO_PERMISSION: Pair<String, String>?
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                return Pair(Manifest.permission.READ_MEDIA_AUDIO, "Camera is needed")
            }
            return null
        }
    val READ_MEDIA_VIDEO_PERMISSION: Pair<String, String>?
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                return Pair(Manifest.permission.READ_MEDIA_VIDEO, "Camera is needed")
            }
            return null
        }

    val startupPermissions = mutableListOf<Pair<String, String>?>(
        READ_EXTERNAL_STORAGE_PERMISSION,
        READ_MEDIA_AUDIO_PERMISSION,
        READ_MEDIA_VIDEO_PERMISSION
    )

    /*fun initialize() {
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val isAllGranted = permissions.values.all { it }

            if (isAllGranted) {
                launchHomeActivity()
            } else {
                permissions.keys.forEach { permission ->
                    if (shouldShowRequestPermissionRationale(permission)) {
                        val fragment = PermissionsRationaleDialog(
                            Pair(permission, PermissionManager.permissions[permission] ?: ""),
                            permissionsRationaleDialogListener
                        )
                        fragment.show(supportFragmentManager, fragment::class.java.simpleName)
                    }
                }
            }
        }

        requestSinglePermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                launchHomeActivity()
            } else {
                finish()
            }
        }

        if (!checkRequiredPermissions()) {
            requestPermissionLauncher.launch(PermissionManager.startupPermissions.mapNotNull {
                it!!.first
            }.toTypedArray())
        }
    }*/

    /*private fun checkRequiredPermissions(): Boolean {
        return PermissionManager.startupPermissions.map { permission ->
            if (permission == null)
                return true
            ContextCompat.checkSelfPermission(
                baseContext,
                permission.first
            ) == PackageManager.PERMISSION_GRANTED
        }.all { it }
    }*/
}