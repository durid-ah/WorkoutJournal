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
    private val bp : T?,
    private val form : BluePrintFormInterface<T>
) : DialogFragment() {

    interface AddEditBluePrintDialogListener<T> {
        fun onAddEditDialogPositiveClick(
            blueprint: T?,
            dialogType: DialogType
        )
        fun onCancelAddEditDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(form.LAYOUT_ID, null)
            builder.setView(formView)

            val dialogType = if (this.bp == null) DialogType.ADD else DialogType.EDIT

            form.lateInitValues(formView, dialogType, bp!!)
            form.setSave()
            form.setCancel()

            builder.create()
        }
    }

}