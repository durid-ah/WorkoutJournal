package com.durid.workoutjournal.ui.workoutBlueprints

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.data.BluePrintRepo
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.adapters.WorkoutBluePrintAdapter
import com.durid.workoutjournal.ui.dialogs.DeleteDialogFragment
import com.durid.workoutjournal.ui.dialogs.WorkoutBluePrintDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorkoutBluePrintFragment : Fragment(),
    WorkoutBluePrintDialogFragment.WorkoutBluePrintDialogListener,
    DeleteDialogFragment.DeleteDialogListener {

    private lateinit var workoutBluePrintViewModel: WorkoutBluePrintViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var wbpAdapter : WorkoutBluePrintAdapter
    private var workoutBluePrintList = ArrayList<WorkoutBluePrint>()

    private fun initRecycler(root : View) {
        recyclerView = root.findViewById(R.id.blue_print_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        wbpAdapter = WorkoutBluePrintAdapter(
            activity!!.applicationContext,
            this,
            workoutBluePrintList
        )
        recyclerView.adapter = wbpAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataSource = BluePrintRepo(activity!!.applicationContext)
        workoutBluePrintViewModel =
            ViewModelProviders.of(this, WorkoutBluePrintViewModelFactory(dataSource))
                .get(WorkoutBluePrintViewModel::class.java)

        val root = inflater.inflate(R.layout.workout_blueprint_fragment, container, false)

        val fab = root.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener{
            showAdd()
        }

        this.initRecycler(root)

        return root
    }

    override fun onResume() {
        super.onResume()

        workoutBluePrintViewModel
            .workoutBluePrint
            .observe(this, Observer {
                wbpAdapter.updateData(it)
            })

        workoutBluePrintViewModel.getWorkoutBluePrints()
    }

    fun showEdit(wbp : WorkoutBluePrint) {
        val dialogFragment = WorkoutBluePrintDialogFragment(wbp, this)
        dialogFragment.show(childFragmentManager, "WorkoutBluePrintDialogFragment")
    }

    private fun showAdd() {
        val dialogFragment = WorkoutBluePrintDialogFragment(null, this)
        dialogFragment.show(childFragmentManager, "WorkoutBluePrintDialogFragment")
    }

    fun showDeleteDialog(Id: String) {
        val dialogFragment = DeleteDialogFragment(Id,this)
        dialogFragment.show(childFragmentManager, "DeleteDialogFragment")
    }

    override fun onEditDialogPositiveClick(
        dialog: DialogInterface,
        wbp: WorkoutBluePrint?,
        dialogType: WorkoutBluePrintDialogFragment.DialogType
    ) {
        if (dialogType == WorkoutBluePrintDialogFragment.DialogType.ADD) {
            workoutBluePrintViewModel.addWorkoutBluePrint(wbp!!)
        } else {
            workoutBluePrintViewModel.editWorkoutBluePrint(wbp!!)
        }

        dialog.dismiss()
    }

    fun launchExerciseBlueprintFragment(Id: String) {
        val action = WorkoutBluePrintFragmentDirections
            .actionNavigationWorkoutBlueprintsToExerciseBluePrintEditFragment(Id)

        findNavController().navigate(action)
    }

    override fun onDeleteDialogPositiveClick(dialog: DialogInterface, Id: String) {
        workoutBluePrintViewModel.deleteWorkoutBluePrint(Id)
        dialog.dismiss()
    }
}