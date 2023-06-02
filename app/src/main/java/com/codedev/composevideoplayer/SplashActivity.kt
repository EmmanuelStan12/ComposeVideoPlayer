package com.codedev.composevideoplayer

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.codedev.base.PermissionManager
import com.codedev.base.PermissionsRationaleDialog
import com.codedev.ui_base_lib.HOME_ACTIVITY
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class SplashActivity : FragmentActivity() {

    private lateinit var requestMultiplePermissionsLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var requestSinglePermissionLauncher: ActivityResultLauncher<String>

    private lateinit var permissionsRationaleDialogListener: PermissionsRationaleDialog.PermissionsRationaleDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
        }

        super.onCreate(savedInstanceState)

        fun launchHomeActivity() {
            Intent(HOME_ACTIVITY).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(it)
            }
        }

        requestSinglePermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    launchHomeActivity()
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }

        requestMultiplePermissionsLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                val isAllGranted = permissions.values.all { it }

                if (!isAllGranted) {

                    val permissionsNotGranted = permissions.filter { !it.value }
                    permissionsNotGranted.keys.forEach { permission ->
                        if (shouldShowRequestPermissionRationale(permission)) {
                            val fragment = PermissionsRationaleDialog(
                                Pair(permission, PermissionManager.permissions[permission] ?: ""),
                                permissionsRationaleDialogListener
                            )
                            fragment.show(supportFragmentManager, fragment::class.java.simpleName)
                        }
                    }
                } else {
                    launchHomeActivity()
                }
            }

        permissionsRationaleDialogListener =
            object : PermissionsRationaleDialog.PermissionsRationaleDialogListener {

                override fun onAccept(permission: String) {
                    requestSinglePermissionLauncher.launch(permission)
                }

                override fun onDecline(permission: String) {

                }
            }


        lifecycleScope.launchWhenCreated {
            delay(2000.milliseconds)

            val isAllPermissionsGranted = checkRequiredPermissions()

            if (!isAllPermissionsGranted) {
                requestMultiplePermissionsLauncher.launch(
                    PermissionManager.startupPermissions.filterNotNull().map {
                        it.first
                    }.toTypedArray()
                )
            } else {
                launchHomeActivity()
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

