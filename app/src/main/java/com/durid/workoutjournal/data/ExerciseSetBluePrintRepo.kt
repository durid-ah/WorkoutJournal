package com.durid.workoutjournal.data

import android.content.ContentValues
import android.content.Context
import com.durid.workoutjournal.model.ExerciseSet
import com.durid.workoutjournal.model.WeightUnit
import java.util.*
import kotlin.collections.ArrayList

class ExerciseSetBluePrintRepo(context : Context) {
    private val repository : RepoBase = RepoBase(context);

    private fun createValueSet (ebp : ExerciseSet) : ContentValues {
        return ContentValues().apply {
            put(DataManager.ID_ROW, ebp.Id)
            put(DataManager.EXERCISE_BP_ID, ebp.ExerciseBluePrintId)
            put(DataManager.EXERCISE_SET_BP_AMOUNT, ebp.Amount)
            put(DataManager.EXERCISE_SET_BP_WEIGHT, ebp.Weight)
            put(DataManager.EXERCISE_SET_BP_WEIGHT_UNIT, ebp.WeightUnit.toString())
        }
    }

    fun addExerciseSetBluePrint(ebp : ExerciseSet) : String? {
        ebp.Id = UUID.randomUUID().toString()

        val values = createValueSet(ebp)
        repository.add(values, DataManager.EXERCISE_SET_BLUEPRINT_TABLE)
        return ebp.Id
    }

    fun editExerciseSetBluePrint(ebp : ExerciseSet) {
        val values = createValueSet(ebp)
       repository.edit(values, DataManager.EXERCISE_SET_BLUEPRINT_TABLE, ebp.Id!!)
    }

    fun deleteExerciseSetBluePrint(id : String) {
        repository.delete(DataManager.EXERCISE_SET_BLUEPRINT_TABLE, id)
    }

    fun getAllExerciseSetBluePrints(Id : String) : ArrayList<ExerciseSet> {
        val cursor = repository.getAllById(
            Id,
            DataManager.EXERCISE_SET_BLUEPRINT_TABLE,
            DataManager.EXERCISE_BP_ID
        )

        val items = arrayListOf<ExerciseSet>()

        with(cursor) {
            while (moveToNext()) {
                val exerciseId = getString(getColumnIndexOrThrow(DataManager.ID_ROW))
                val exerciseBpId = getString(getColumnIndexOrThrow(DataManager.EXERCISE_BP_ID))
                val amount = getString(getColumnIndexOrThrow(DataManager.EXERCISE_SET_BP_AMOUNT))
                val weight = getString(getColumnIndexOrThrow(DataManager.EXERCISE_SET_BP_WEIGHT))
                val weightUnit = getString(getColumnIndexOrThrow(DataManager.EXERCISE_SET_BP_WEIGHT_UNIT))

                items.add(
                    ExerciseSet(
                        exerciseId,
                        exerciseBpId,
                        amount,
                        weight.toInt(),
                        WeightUnit.valueOf(weightUnit)
                    )
                )
            }
        }

        return items
    }
}