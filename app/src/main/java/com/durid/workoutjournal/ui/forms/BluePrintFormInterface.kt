package com.durid.workoutjournal.ui.forms

import android.content.DialogInterface
import android.view.View
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog

interface BluePrintFormInterface<T> {
    var item : T?
    val listener : AddEditBluePrintDialog.AddEditBluePrintDialogListener<T>
    var dialogType: DialogType
    val LAYOUT_ID : Int

    fun lateInitValues(
        formView: View,
        dialogType: DialogType,
        item : T?
    )
    fun setSave()
    fun setCancel()
}