package com.durid.workoutjournal.ui.forms

import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog

class WorkoutBluePrintAddEditForm() : BluePrintFormInterface<WorkoutBluePrint> {

    override lateinit var dialogType: DialogType
    override lateinit var item: WorkoutBluePrint
    override lateinit var dialog: DialogInterface
    override lateinit var listener
            : AddEditBluePrintDialog.AddEditBluePrintDialogListener<WorkoutBluePrint>

    private lateinit var nameText : TextView
    private lateinit var descriptionText : TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override val LAYOUT_ID: Int
        get() = R.layout.workout_blueprint_dialog

    // NOTE TO SELF: since form view and listener cannot be assigned at initiation they need
    // to be added later on in the process.
    // TODO: Work on adding in dialogType as well as a better way to handle the blueprint and the
    // TODO: listener, check for the possibility of the blueprint to be null
    override fun lateInitValues(
        formView : View,
        listener: AddEditBluePrintDialog.AddEditBluePrintDialogListener<WorkoutBluePrint>
    ) {
        this.listener = listener

        nameText = formView.findViewById(R.id.blueprintName)
        descriptionText = formView.findViewById(R.id.blueprintDescription)
        saveButton = formView.findViewById(R.id.saveButton)
        cancelButton = formView.findViewById(R.id.cancelButton)
    }

    override fun setSave() {
        saveButton.setOnClickListener {
            if (dialogType == DialogType.ADD) {
                item = WorkoutBluePrint(null,
                    nameText.text.toString(),
                    descriptionText.text.toString(), null)
            } else {
                item!!.Name = nameText.text.toString()
                item!!.Description = descriptionText.text.toString()
            }

            listener.onEditDialogPositiveClick(dialog, item, dialogType)
        }
    }

    override fun setCancel() {
        cancelButton.setOnClickListener {
            dialog.cancel()
        }
    }
}