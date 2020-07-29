package com.durid.workoutjournal.ui.forms

import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.WorkoutType
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog
import kotlinx.android.synthetic.main.exercise_bp_dialog.view.*

class ExerciseBluePrintAddEditForm(
    override var item: ExerciseBluePrint?,
    override val listener: AddEditBluePrintDialog.AddEditBluePrintDialogListener<ExerciseBluePrint>,
    val wbpId : String
) : BluePrintFormInterface<ExerciseBluePrint> {

    private lateinit var exerciseBluePrintName : TextView
    private lateinit var exerciseBluePrintInfo : TextView
    private lateinit var radioGroup: RadioGroup

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override lateinit var dialogType: DialogType
    override val LAYOUT_ID: Int
        get() = R.layout.exercise_bp_dialog

    override fun lateInitValues(formView: View) {
        exerciseBluePrintName = formView.findViewById(R.id.exerciseBlueprintName)
        exerciseBluePrintInfo = formView.findViewById(R.id.exerciseBlueprintInfo)
        radioGroup = formView.findViewById(R.id.radioGroup)

        saveButton = formView.findViewById(R.id.saveButton)
        cancelButton = formView.findViewById(R.id.cancelButton)

        // Set the dialog type based on the model object
        dialogType = if (item != null) DialogType.EDIT else DialogType.ADD

        // If it is an edit dialog type fill the fields with the model data
        if (dialogType == DialogType.EDIT) {
            exerciseBluePrintName.text = item!!.ExerciseName
            exerciseBluePrintInfo.text = item!!.Info

            if (item!!.WorkoutType == WorkoutType.TIMED) {
                radioGroup.timedRadioButton.isChecked = true
            } else {
                radioGroup.repsRadioButton.isChecked = true
            }
        }
    }

    override fun setSave() {
        saveButton.setOnClickListener {
            val checkedId = radioGroup.checkedRadioButtonId
            val exerciseBpName = exerciseBluePrintName.text.toString()
            val exerciseBpInfo = exerciseBluePrintInfo.text.toString()
            val workoutType = if (checkedId == radioGroup.repsRadioButton.id) {
                WorkoutType.REPS
            } else {
                WorkoutType.TIMED
            }

            if (dialogType == DialogType.ADD) {
                item = ExerciseBluePrint(
                    null,
                    exerciseBpName,
                    wbpId,
                    exerciseBpInfo,
                    workoutType,
                    null
                )
            } else {
                item!!.ExerciseName = exerciseBpName
                item!!.Info = exerciseBpInfo
                item!!.WorkoutType = workoutType
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