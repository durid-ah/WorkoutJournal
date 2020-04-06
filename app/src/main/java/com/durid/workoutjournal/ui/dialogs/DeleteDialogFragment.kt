package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import java.lang.ClassCastException

class DeleteDialogFragment(
    private val id: String,
    private val fragment : DeleteDialogListener
) : DialogFragment() {

    lateinit var cancelButton : Button
    lateinit var confirmButton : Button
    lateinit var listener : DeleteDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(R.layout.delete_dialog, null)

            try {
                listener = fragment
            } catch (ex : ClassCastException) {
                throw ex
            }

            setupButtons(formView)
            builder.setView(formView)

            confirmButton.setOnClickListener {
                listener.onDeleteDialogPositiveClick(dialog, id)
            }

            cancelButton.setOnClickListener {
                dialog.cancel()
            }

            builder.create()
        }
    }

    fun setupButtons(v : View) {
        cancelButton = v.findViewById(R.id.cancelButton)
        confirmButton = v.findViewById(R.id.confirmButton)
    }

    interface DeleteDialogListener {
        fun onDeleteDialogPositiveClick(
            dialog: DialogInterface,
            Id : String
        )
    }
}