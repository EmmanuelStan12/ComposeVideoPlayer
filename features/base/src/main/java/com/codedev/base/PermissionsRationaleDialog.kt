package com.codedev.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.DialogFragment
import com.codedev.base.R
import com.codedev.base.databinding.DialogPermissionRationaleBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class PermissionsRationaleDialog(
    private val permission: Pair<String, String>,
    private val permissionRationaleDialogListener: PermissionsRationaleDialogListener
) : DialogFragment() {

    interface PermissionsRationaleDialogListener {
        fun onAccept(permission: String)

        fun onDecline(permission: String)
    }

    override fun isCancelable(): Boolean  = false

    private lateinit var binding: DialogPermissionRationaleBinding

    override fun getTheme(): Int {
        return R.style.RoundedCornersDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogPermissionRationaleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {

            tvAcceptDialogPermissionsRationale.setOnClickListener {
                permissionRationaleDialogListener.onAccept(permission.first)
            }

            tvCancelDialogPermissionsRationale.setOnClickListener {
                permissionRationaleDialogListener.onDecline(permission.second)
            }

            tvTextPermissionsRationale.setText(permission.first)
        }
    }
}