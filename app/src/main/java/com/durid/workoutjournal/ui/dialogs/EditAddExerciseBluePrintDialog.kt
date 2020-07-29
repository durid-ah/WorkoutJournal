package com.durid.workoutjournal.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.ui.exerciseBluePrintEdit.ExerciseBluePrintEditFragment

class EditAddExerciseBluePrintDialog(
    var ebp : ExerciseBluePrint?,
    private val fragment : ExerciseBluePrintEditFragment
) : DialogFragment() {

    interface EditAddExerciseBluePrintDialogListener {
        fun onEditDialogPositiveClick(
            dialog: DialogInterface,
            wbp: ExerciseBluePrint?,
            dialogType: DialogType
        )
    }

    private lateinit var listener : EditAddExerciseBluePrintDialogListener
    private lateinit var dialogType: DialogType
    private lateinit var exerciseBluePrintName : TextView
    private lateinit var exerciseBluePrintInfo : TextView
    private lateinit var radioGroup: RadioGroup


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val formView = inflater.inflate(R.layout.exercise_bp_dialog, null)

            try {
                //listener = fragment
            } catch (ex : ClassCastException) {
                throw ex
            }

            builder.create()
        }
    }
}