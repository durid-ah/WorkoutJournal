package com.durid.workoutjournal.ui.forms

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog

/**
 * Implementing the Form interface for the @param[WorkoutBluePrint]
 */
class WorkoutBluePrintAddEditForm(
    override val listener: AddEditBluePrintDialog.AddEditBluePrintDialogListener<WorkoutBluePrint>,
    override var item: WorkoutBluePrint?
) : BluePrintFormInterface<WorkoutBluePrint>
{

    // The form fields
    private lateinit var nameText : TextView
    private lateinit var descriptionText : TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override lateinit var dialogType: DialogType
    override val LAYOUT_ID: Int
        get() = R.layout.workout_blueprint_dialog

    override fun lateInitValues(formView : View) {

        // Initialize the form fields
        nameText = formView.findViewById(R.id.blueprintName)
        descriptionText = formView.findViewById(R.id.blueprintDescription)
        saveButton = formView.findViewById(R.id.saveButton)
        cancelButton = formView.findViewById(R.id.cancelButton)

        // Set the dialog type based on the model object
        dialogType = if (item != null) DialogType.EDIT else DialogType.ADD

        // If it is an edit dialog type fill the fields with the model data
        if (dialogType == DialogType.EDIT) {
            nameText.text = item!!.Name
            descriptionText.text = item!!.Description
        }
    }

    override fun setSave() {
        saveButton.setOnClickListener {
            // If it is an add instantiate a new object with the form data
            if (dialogType == DialogType.ADD) {
                item = WorkoutBluePrint(
                    null,
                    nameText.text.toString(),
                    descriptionText.text.toString(),
                    null
                )
            // Else update the current object
            } else {
                item!!.Name = nameText.text.toString()
                item!!.Description = descriptionText.text.toString()
            }

            listener.onAddEditDialogPositiveClick(item, dialogType)
        }
    }

    override fun setCancel() {
        cancelButton.setOnClickListener {
            listener.onCancelAddEditDialog()
        }
    }
}