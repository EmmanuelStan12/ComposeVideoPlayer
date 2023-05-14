package com.codedev.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.codedev.base.DefaultTheme
import com.codedev.base.PermissionManager
import com.codedev.base.PermissionsRationaleDialog
import timber.log.Timber

class HomeActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            DefaultTheme {

                val requestSinglePermissionState =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->

                    }

                val permissionsRationaleDialogListener: PermissionsRationaleDialog.PermissionsRationaleDialogListener =
                    object: PermissionsRationaleDialog.PermissionsRationaleDialogListener {
                        override fun onAccept(permission: String) {
                            requestSinglePermissionState.launch(permission)
                        }

                        override fun onDecline(permission: String) {

                        }
                    }
                
                val requestMultiplePermissionState =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                        val isAllGranted = permissions.values.all { it }

                        if (!isAllGranted) {
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

                val lifecycleOwner = LocalLifecycleOwner.current

                DisposableEffect(key1 = lifecycleOwner) {

                    val observer = LifecycleEventObserver { owner, event ->
                        Timber.e("owner - $owner and event - $event")
                        if (event == Lifecycle.Event.ON_START) {
                            val isAllPermissionsGranted = checkRequiredPermissions()

                            if (!isAllPermissionsGranted) {
                                requestMultiplePermissionState.launch(PermissionManager.startupPermissions.filterNotNull().map {
                                    it.first
                                }.toTypedArray())
                            }
                        }
                    }

                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
                
                HomeContainer()
            }
        }
    }
    
    private fun checkRequiredPermissions(): Boolean {
        return PermissionManager.startupPermissions.map { permission ->
            if (permission == null) true
            else
                ContextCompat.checkSelfPermission(
                    baseContext,
                    permission.first
                ) == PackageManager.PERMISSION_GRANTED
        }.all { it }
    }
}