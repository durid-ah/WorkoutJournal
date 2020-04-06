package com.durid.workoutjournal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.WorkoutBluePrint
import com.durid.workoutjournal.ui.workoutBlueprints.WorkoutBluePrintFragment

class WorkoutBluePrintAdapter(
    private val context: Context,
    private val wbpFragment: WorkoutBluePrintFragment,
    private var data : ArrayList<WorkoutBluePrint>)
    : RecyclerView.Adapter<WorkoutBluePrintAdapter.BluePrintHolder>() {

    fun updateData(newData : ArrayList<WorkoutBluePrint>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluePrintHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.workout_blueprint_card, parent, false)

        return BluePrintHolder(view)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: BluePrintHolder, position: Int) {
        val wbp = data[position]
        holder.setDetails(wbp)
    }

    inner class BluePrintHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var titleView : TextView = itemView.findViewById(R.id.title_text)
        private var detailView : TextView = itemView.findViewById(R.id.detail_text)

        // The extended buttons
        private var exercisesButton : TextView = itemView.findViewById(R.id.exercisesButton)
        private var editButton : Button = itemView.findViewById(R.id.editButton)
        private var deleteButton : Button = itemView.findViewById(R.id.deleteButton)

        // Minimized section of the cardView
        private var minBlueprintCard : LinearLayout = itemView.findViewById(R.id.min_workout_bp_card)

        fun setDetails(wbp : WorkoutBluePrint) {
            titleView.text = wbp.Name
            detailView.text = wbp.Description

            minBlueprintCard.setOnClickListener {
                if ( exercisesButton.visibility == View.GONE ) {
                    setButtonVisibility(View.VISIBLE)   // Turn on the buttons
                } else {
                    setButtonVisibility(View.GONE)  // Turn off buttons
                }
            }

            exercisesButton.setOnClickListener {
                wbpFragment.launchExerciseBlueprintFragment(wbp.Id!!)
            }

            editButton.setOnClickListener {
                wbpFragment.showEdit(wbp)
            }

            deleteButton.setOnClickListener {
                wbpFragment.showDeleteDialog(wbp.Id!!)
            }
        }

        private fun setButtonVisibility(setting : Int) {
            exercisesButton.visibility = setting
            editButton.visibility = setting
            deleteButton.visibility = setting
        }
    }
}