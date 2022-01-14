package com.verindrarizya.suitmediatest.ui.firstscreen

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class PalindromeDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(arguments?.getString(PALINDROME_STATE))
                .setPositiveButton("Close", DialogInterface.OnClickListener {dialog, id ->

                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val PALINDROME_STATE = "palindrome_state"
    }
}