package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import java.lang.ClassCastException

class ConfirmDialogFragment(
    private val id: String,
    private val message : String,
    private val fragment : ConfirmDialogListener
) : DialogFragment() {

    lateinit var cancelButton : Button
    lateinit var confirmButton : Button
    lateinit var dialogMessage : TextView
    lateinit var listener : ConfirmDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(R.layout.confirm_dialog, null)

            try {
                listener = fragment
            } catch (ex : ClassCastException) {
                throw ex
            }

            setupDialogView(formView)
            builder.setView(formView)

            dialogMessage.text = message

            confirmButton.setOnClickListener {
                listener.onDeleteDialogPositiveClick(dialog!!, id)
            }

            cancelButton.setOnClickListener {
                dialog!!.cancel()
            }

            builder.create()
        }
    }

    private fun setupDialogView(v : View) {
        dialogMessage = v.findViewById(R.id.dialogMessage)
        cancelButton = v.findViewById(R.id.cancelButton)
        confirmButton = v.findViewById(R.id.confirmButton)
    }

    interface ConfirmDialogListener {
        fun onDeleteDialogPositiveClick(
            dialog: DialogInterface,
            Id : String
        )
    }
}