package com.durid.workoutjournal.ui.forms

import android.view.View
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog

/**
 * The interface that handles the form in the Add/Edit dialog
 * @param T model type
 */
interface BluePrintFormInterface<T> {
    // The model object
    var item : T?

    // Handles the submit and cancel buttons in the form
    val listener : AddEditBluePrintDialog.AddEditBluePrintDialogListener<T>

    // Is the dialog for adding or editing
    var dialogType: DialogType

    // The Id of the form layout to be used in the dialog
    val LAYOUT_ID : Int

    // Initialize the values that can't be initialized immediately
    fun lateInitValues(formView: View)

    // Sets the save action
    fun setSave()

    // Sets the cancel action
    fun setCancel()
}