package com.durid.workoutjournal.ui.exerciseBluePrintEdit

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.data.ExerciseBluePrintRepo
import com.durid.workoutjournal.data.ExerciseSetBluePrintRepo
import com.durid.workoutjournal.model.DialogType
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.ExerciseSet
import com.durid.workoutjournal.ui.adapters.ExerciseBluePrintAdapter
import com.durid.workoutjournal.ui.dialogs.AddEditBluePrintDialog
import com.durid.workoutjournal.ui.dialogs.ConfirmDialogFragment
import com.durid.workoutjournal.ui.forms.ExerciseBluePrintAddEditForm
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseBluePrintEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseBluePrintEditFragment:
    AddEditBluePrintDialog.AddEditBluePrintDialogListener<ExerciseBluePrint>,
    ConfirmDialogFragment.ConfirmDialogListener,
    Fragment() {

    private lateinit var exerciseBluePrintViewModel: ExerciseBluePrintViewModel
    private lateinit var addEditDialogFragment : AddEditBluePrintDialog<ExerciseBluePrint>

    private val args : ExerciseBluePrintEditFragmentArgs by navArgs()
    private var exerciseBluePrintList = ArrayList<ExerciseBluePrint>()
    private lateinit var workoutId : String
    private lateinit var ebpAdapter : ExerciseBluePrintAdapter

    private val CONFIRM_DIALOG_DELETE_MESSAGE =
        "Are you sure you would like to delete this exercise and its sets?"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        workoutId = args.workoutId
        val dataSource = ExerciseBluePrintRepo(requireActivity().applicationContext)
        val secondarySource = ExerciseSetBluePrintRepo(requireActivity().applicationContext)

        exerciseBluePrintViewModel =
                ViewModelProviders.of(
                    this, ExerciseBluePrintViewModelFactory(dataSource, secondarySource)
                ).get(ExerciseBluePrintViewModel::class.java)

        val root = inflater.inflate(
            R.layout.fragment_exercise_blue_print_edit, container, false
        )

        val fab = root.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        fab.setOnClickListener{ showAddExerciseDialog() }

        val recyclerView = root.findViewById<RecyclerView>(R.id.exerciseRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext)

        ebpAdapter = ExerciseBluePrintAdapter(
            requireActivity().applicationContext,
            this,
            exerciseBluePrintList
        )

        recyclerView.adapter = ebpAdapter

        return root
    }

    fun addSet(esBp : ExerciseSet) {
        exerciseBluePrintViewModel.addExerciseSet(esBp)
    }


    override fun onResume() {
        super.onResume()
        exerciseBluePrintViewModel
            .exerciseBluePrint
            .observe(this, Observer {
                ebpAdapter.updateData(it)
            })

        exerciseBluePrintViewModel.getExerciseBluePrints(workoutId)
    }

    fun showEditExerciseDialog(bp : ExerciseBluePrint) {
        addEditDialogFragment = AddEditBluePrintDialog(
            ExerciseBluePrintAddEditForm(bp, this, workoutId)
        )

        addEditDialogFragment.show(childFragmentManager, "EditExerciseBluePrintDialogFragment")
    }

    fun showDeleteDialog(Id : String) {
        val dialogFragment = ConfirmDialogFragment(Id, CONFIRM_DIALOG_DELETE_MESSAGE,this)
        dialogFragment.show(childFragmentManager, "DeleteDialogFragment")
    }

    private fun showAddExerciseDialog() {
        addEditDialogFragment = AddEditBluePrintDialog(
            ExerciseBluePrintAddEditForm(null, this, workoutId)
        )

        addEditDialogFragment.show(childFragmentManager, "ExerciseBluePrintDialogFragment")
    }

    override fun onAddEditDialogPositiveClick(
        blueprint: ExerciseBluePrint?,
        dialogType: DialogType
    ) {
        if (dialogType == DialogType.ADD) {
            exerciseBluePrintViewModel.addExerciseBluePrint(blueprint!!)
        } else {
            exerciseBluePrintViewModel.editExerciseBluePrint(blueprint!!)
        }

        addEditDialogFragment.dismiss()
    }

    override fun onCancelAddEditDialog() {
        addEditDialogFragment.dismiss()
    }

    override fun onDeleteDialogPositiveClick(dialog: DialogInterface, Id: String) {
        exerciseBluePrintViewModel.deleteExerciseBluePrint(Id, workoutId)
        dialog.dismiss()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseBluePrintEditFragment()
    }
}
