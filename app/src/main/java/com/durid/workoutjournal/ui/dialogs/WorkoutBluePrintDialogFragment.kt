package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.workoutBlueprints.WorkoutBluePrintFragment

class WorkoutBluePrintDialogFragment(var wbp : WorkoutBluePrint?,
                                     private val fragment: WorkoutBluePrintFragment)
                                        : DialogFragment() {

    private lateinit var listener: WorkoutBluePrintDialogListener
    private lateinit var dialogType : DialogType
    private lateinit var nameText : TextView
    private lateinit var descriptionText : TextView
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {

            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)

            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(R.layout.workout_blueprint_dialog, null)

            try {
                listener = fragment
            } catch (ex : ClassCastException) {
                throw ex
            }

            setDialogType(wbp != null)
            setUpFormItems(formView)
            builder.setView(formView)

            saveButton.setOnClickListener {
                if (dialogType == DialogType.ADD) {
                        wbp = WorkoutBluePrint(null,
                            nameText.text.toString(),
                            descriptionText.text.toString(), null)
                    } else {
                        wbp!!.Name = nameText.text.toString()
                        wbp!!.Description = descriptionText.text.toString()
                    }

                listener.onEditDialogPositiveClick(dialog, wbp, dialogType)
            }

            cancelButton.setOnClickListener {
                dialog.cancel()
            }

            builder.create()
        }
    }

    private fun setDialogType(add : Boolean) {
        dialogType = if (add) DialogType.EDIT else DialogType.ADD
    }

    private fun setUpFormItems(view: View) {
        nameText = view.findViewById(R.id.blueprintName)
        descriptionText = view.findViewById(R.id.blueprintDescription)
        saveButton = view.findViewById(R.id.saveButton)
        cancelButton = view.findViewById(R.id.cancelButton)

        if (dialogType == DialogType.EDIT) {
            nameText.text = wbp!!.Name
            descriptionText.text = wbp!!.Description
        }
    }

    enum class DialogType {
        ADD, EDIT
    }

    interface WorkoutBluePrintDialogListener {
        fun onEditDialogPositiveClick(dialog: DialogInterface,
                                      wbp: WorkoutBluePrint?,
                                      dialogType: DialogType)
    }

}