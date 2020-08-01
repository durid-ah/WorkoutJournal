package com.durid.workoutjournal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.ExerciseSet
import com.durid.workoutjournal.model.WeightUnit
import com.durid.workoutjournal.ui.exerciseBluePrintEdit.ExerciseBluePrintEditFragment

class ExerciseBluePrintAdapter (
    private val context: Context,
    private val ebpFragment : ExerciseBluePrintEditFragment,
    private var data : ArrayList<ExerciseBluePrint>
) : RecyclerView.Adapter<ExerciseBluePrintAdapter.ExerciseHolder>() {

    fun updateData(newData : ArrayList<ExerciseBluePrint>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.exercise_blueprint_card, parent, false)

        return ExerciseHolder(view)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val ebp = data[position]
        holder.setDetails(ebp)
    }

    inner class ExerciseHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseName = itemView.findViewById<TextView>(R.id.exerciseName)
        private val workoutType =  itemView.findViewById<TextView>(R.id.workoutType)
        private val infoButton = itemView.findViewById<Button>(R.id.infoButton)

        // Exercise buttons
        private val editExerciseButton = itemView.findViewById<Button>(R.id.editExerciseButton)
        private val deleteExerciseButton : Button = itemView.findViewById(R.id.deleteExerciseButton)

        private val exerciseSetRecyclerView = itemView
            .findViewById<RecyclerView>(R.id.exercise_set_recycler_view)

        private val minExerciseBluePrint = itemView
            .findViewById<ConstraintLayout>(R.id.min_exercise_bp_card)

        fun setDetails(ebp : ExerciseBluePrint) {
            exerciseName.text = ebp.ExerciseName
            workoutType.text = ebp.WorkoutType.toString()

            minExerciseBluePrint.setOnClickListener {
                if (exerciseSetRecyclerView.visibility == View.GONE) {
                    exerciseSetRecyclerView.visibility = View.VISIBLE
                    setButtonVisibility(View.VISIBLE)
                } else {
                    exerciseSetRecyclerView.visibility = View.GONE
                    setButtonVisibility(View.GONE)
                }
            }

            editExerciseButton.setOnClickListener { ebpFragment.showEditExerciseDialog(ebp) }
            deleteExerciseButton.setOnClickListener { /*TODO: Fill this up*/ }

            exerciseSetRecyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = ExerciseSetBpAdapter(
                context,
                ebp.WorkoutType,
                ebp.Sets!!
            )

            exerciseSetRecyclerView.adapter = adapter
        }


        fun setButtonVisibility(setting : Int) {
            editExerciseButton.visibility = setting
            deleteExerciseButton.visibility = setting
        }
    }
}