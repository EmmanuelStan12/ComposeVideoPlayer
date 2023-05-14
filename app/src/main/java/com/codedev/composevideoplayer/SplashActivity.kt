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
import androidx.lifecycle.lifecycleScope
import com.codedev.base.PermissionManager
import com.codedev.base.PermissionsRationaleDialog
import com.codedev.ui_base_lib.HOME_ACTIVITY
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }

        super.onCreate(savedInstanceState)

        fun launchHomeActivity() {
            Intent(HOME_ACTIVITY).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(it)
            }
        }





        lifecycleScope.launchWhenCreated {
            delay(2000.milliseconds)

            launchHomeActivity()
        }

    }
}
