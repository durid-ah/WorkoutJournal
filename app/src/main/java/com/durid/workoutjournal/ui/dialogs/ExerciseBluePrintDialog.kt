package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.ui.exerciseBluePrintEdit.ExerciseBluePrintEditFragment

class ExerciseBluePrintDialog(
    var ebp : ExerciseBluePrint?,
    private val fragment : ExerciseBluePrintEditFragment
) : DialogFragment() {

    interface ExerciseBluePrintDialogListener {
        fun onEditDialogPositiveClick(
            dialog: DialogInterface,
            wbp: ExerciseBluePrint?,
            dialogType: DialogType
        )
    }

    private lateinit var listener : ExerciseBluePrintDialogListener
    private lateinit var dialogType: DialogType
    // TODO: add dialog layout fields

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(R.layout.exercise_bp_dialog, null)

            TODO("Finish set-up")

            builder.create()
        }
    }
}