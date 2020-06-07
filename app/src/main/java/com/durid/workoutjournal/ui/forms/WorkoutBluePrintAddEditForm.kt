package com.durid.workoutjournal.ui.forms

import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog

class WorkoutBluePrintAddEditForm(
    override val listener: AddEditBluePrintDialog.AddEditBluePrintDialogListener<WorkoutBluePrint>
) : BluePrintFormInterface<WorkoutBluePrint>
{

    override lateinit var dialogType: DialogType
    override var item: WorkoutBluePrint? = null //TODO: Move to constructor from lateInitValues() ?

    private lateinit var nameText : TextView
    private lateinit var descriptionText : TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override val LAYOUT_ID: Int
        get() = R.layout.workout_blueprint_dialog

    override fun lateInitValues(
        formView : View,
        dialogType: DialogType,
        item : WorkoutBluePrint?
    ) {

        nameText = formView.findViewById(R.id.blueprintName)
        descriptionText = formView.findViewById(R.id.blueprintDescription)
        saveButton = formView.findViewById(R.id.saveButton)
        cancelButton = formView.findViewById(R.id.cancelButton)

        this.dialogType = dialogType
        this.item = item

        if (dialogType == DialogType.EDIT) {
            nameText.text = item!!.Name
            descriptionText.text = item.Description
        }
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

            listener.onAddEditDialogPositiveClick(item, dialogType)
        }
    }

    override fun setCancel() {
        cancelButton.setOnClickListener {
            listener.onCancelAddEditDialog()
        }
    }
}