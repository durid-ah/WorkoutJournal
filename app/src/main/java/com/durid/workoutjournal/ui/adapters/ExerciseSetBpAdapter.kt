package com.durid.workoutjournal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durid.workoutjournal.R
import com.durid.workoutjournal.model.ExerciseSet

class ExerciseSetBpAdapter (
    private val context: Context,
    private val data : ArrayList<ExerciseSet>
) : RecyclerView.Adapter<ExerciseSetBpAdapter.SetHolder>() {

    inner class SetHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun setDetails(esBp : ExerciseSet) {
            val repsTimeText = itemView.findViewById<TextView>(R.id.repsTimeText)
            val weightAmountText = itemView.findViewById<TextView>(R.id.weightAmountText)
            val weightUnitText = itemView.findViewById<TextView>(R.id.weightUnitText)

            repsTimeText.text = esBp.Amount
            weightAmountText.text = esBp.Weight.toString()
            weightUnitText.text = esBp.WeightUnit.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.exercise_set, parent, false)

        return SetHolder(view)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: SetHolder, position: Int) {
        val esBp = data[position]
        holder.setDetails(esBp)
    }
}