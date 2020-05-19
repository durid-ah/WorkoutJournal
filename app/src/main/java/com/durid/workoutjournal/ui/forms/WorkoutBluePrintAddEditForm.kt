package com.durid.workoutjournal.ui.forms

import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.WorkoutBluePrint

//TODO("Implement and test workout blueprint version")

class WorkoutBluePrintAddEditForm :
    BluePrintFormInterface<WorkoutBluePrint>{
    override lateinit var dialogType: DialogType
    private lateinit var nameText : TextView
    private lateinit var descriptionText : TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    override fun InitFields(formView: View, dialogType: DialogType) {
        TODO("Not yet implemented")
    }

    override fun SetSave(item: WorkoutBluePrint?, dialogType: DialogType) {
        TODO("Not yet implemented")
    }

    override fun SetCancel(dialog: DialogInterface) {
        TODO("Not yet implemented")
    }
}