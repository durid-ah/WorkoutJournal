package com.durid.workoutjournal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.durid.workoutjournal.R
import com.durid.workoutjournal.data.BluePrintRepo
import com.durid.workoutjournal.data.DataManager
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.ExerciseSet
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.model.WorkoutType

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bpRepo = BluePrintRepo(activity!!.applicationContext)

//        val es = ExerciseSet("Test", null, "lbs")
//        val ess = ArrayList<ExerciseSet>()
//        ess.add(es)

//        val EBP = ExerciseBluePrint("Test EBP",
//                                    "",
//                                    "Test Ebp",
//                                    "info",
//                                    WorkoutType.REPS,
//                                    ess)

//        val EBPs = ArrayList<ExerciseBluePrint>()
//        EBPs.add(EBP)

//        var WBP = WorkoutBluePrint( Name = "TEST WBP",
//                                    Description = "TEST WBP",
//                                    Exercises = ArrayList(), Id = null)
//
//        bpRepo.addWorkoutBluePrint(WBP)
        //var stuff = bpRepo.getAllWorkoutBluePrints()

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}