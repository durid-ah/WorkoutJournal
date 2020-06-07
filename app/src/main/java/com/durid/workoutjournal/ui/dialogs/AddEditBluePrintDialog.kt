package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.ui.forms.BluePrintFormInterface

/**
 * A generic dialog that handles the add/edit of different models
 * @param T the model type
 * @property form the object that handles all the form functionality
 */
class AddEditBluePrintDialog<T>(
    private val form : BluePrintFormInterface<T>
) : DialogFragment() {

    /**
     * The interface that handles the confirm and cancel buttons
     * @param T the model type
     */
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

            form.lateInitValues(formView)
            form.setSave()
            form.setCancel()

            builder.create()
        }
    }

}