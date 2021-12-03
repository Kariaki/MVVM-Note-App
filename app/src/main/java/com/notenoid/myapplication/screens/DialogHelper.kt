package com.notenoid.myapplication.screens

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.notenoid.myapplication.R

object DialogHelper {

    fun dialog(

        context: Context,
        activity: Activity,
        action: () -> Unit
    ): AlertDialog {


        val alertDialog: AlertDialog = activity.let {
            val builder = MaterialAlertDialogBuilder(
                context,
                R.style.dialog_theme
            ).apply {
                setPositiveButton("Sure",
                    DialogInterface.OnClickListener { dialog, id ->
                        action()
                    })
                setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
                background = ContextCompat.getDrawable(context, R.drawable.dialog_background)

            }
            // Set other dialog properties


            // Create the AlertDialog
            builder.create()
        }

        return alertDialog
    }

}