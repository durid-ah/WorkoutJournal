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
import com.durid.workoutjournal.ui.dialogs.EditAddExerciseBluePrintDialog

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseBluePrintEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseBluePrintEditFragment:
    EditAddExerciseBluePrintDialog.EditAddExerciseBluePrintDialogListener,
    Fragment() {

    private lateinit var exerciseBluePrintViewModel: ExerciseBluePrintViewModel

    private val args : ExerciseBluePrintEditFragmentArgs by navArgs()
    private var exerciseBluePrintList = ArrayList<ExerciseBluePrint>()
    private lateinit var id : String
    private lateinit var ebpAdapter : ExerciseBluePrintAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        id = args.workoutId
        val dataSource = ExerciseBluePrintRepo(activity!!.applicationContext)
        val secondarySource = ExerciseSetBluePrintRepo(activity!!.applicationContext)

        exerciseBluePrintViewModel =
                ViewModelProviders.of(
                    this,
                    ExerciseBluePrintViewModelFactory(dataSource, secondarySource)
                    ).get(ExerciseBluePrintViewModel::class.java)

        val root = inflater.inflate(
            R.layout.fragment_exercise_blue_print_edit,
            container,
            false
        )

        val recyclerView = root.findViewById<RecyclerView>(R.id.exerciseRecycler)
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        ebpAdapter = ExerciseBluePrintAdapter(
            activity!!.applicationContext,
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

        exerciseBluePrintViewModel.getExerciseBluePrints(id)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseBluePrintEditFragment()
    }

    override fun onEditDialogPositiveClick(
        dialog: DialogInterface,
        wbp: ExerciseBluePrint?,
        dialogType: DialogType
    ) {
        TODO("Not yet implemented")
    }
}
