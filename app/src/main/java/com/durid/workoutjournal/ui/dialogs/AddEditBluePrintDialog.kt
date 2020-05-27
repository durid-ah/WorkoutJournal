package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.ui.forms.BluePrintFormInterface


class AddEditBluePrintDialog<T>(
    bp : T?,
    private val form : BluePrintFormInterface<T>,
    private val listener : AddEditBluePrintDialogListener<T>
) : DialogFragment() {
    private lateinit var dialogType : DialogType

    interface AddEditBluePrintDialogListener<T> {
        fun onEditDialogPositiveClick(
            dialog: DialogInterface,
            blueprint: T?,
            dialogType: DialogType
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(form.LAYOUT_ID, null)

            form.lateInitValues(formView, listener)
            builder.setView(formView)
            form.setSave()
            form.setCancel()

            builder.create()
        }
    }

}