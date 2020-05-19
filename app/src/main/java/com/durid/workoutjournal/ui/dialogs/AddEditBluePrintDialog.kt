package com.durid.workoutjournal.ui.dialogs

import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.model.DialogType


class AddEditBluePrintDialog<T>(
    bp : T?,
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


    private fun setDialogType(add : Boolean) {
        dialogType = if (add) DialogType.EDIT else DialogType.ADD
    }

}