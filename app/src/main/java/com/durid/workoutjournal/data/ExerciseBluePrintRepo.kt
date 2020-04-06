package com.durid.workoutjournal.data

import android.content.ContentValues
import android.content.Context
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.WorkoutType
import java.util.*
import kotlin.collections.ArrayList

class ExerciseBluePrintRepo(context: Context) {
    private val repository : RepoBase = RepoBase(context);

    private fun createValueSet (ebp : ExerciseBluePrint) : ContentValues {
        return ContentValues().apply {
            put(DataManager.ID_ROW, ebp.Id)
            put(DataManager.EXERCISE_BP_NAME, ebp.ExerciseName)
            put(DataManager.EXERCISE_BP_WORKOUT_BLUEPRINT_ID, ebp.WorkoutBluePrintId)
            put(DataManager.EXERCISE_BP_INFO, ebp.Info)
            put(DataManager.EXERCISE_BP_WORKOUT_TYPE, ebp.WorkoutType.toString())
        }
    }

    fun addExerciseBluePrint(ebp : ExerciseBluePrint) : String? {
        ebp.Id = UUID.randomUUID().toString()

        val values = createValueSet(ebp)
        repository.add(values, DataManager.EXERCISE_BLUEPRINT_TABLE)

        return ebp.Id
    }

    fun editExerciseBluePrint(ebp : ExerciseBluePrint) {
        val values = createValueSet(ebp)
        repository.edit(values, DataManager.EXERCISE_BLUEPRINT_TABLE, ebp.Id!!)
    }

    fun deleteExerciseBluePrint(id : String) {
        repository.delete(id, DataManager.EXERCISE_BLUEPRINT_TABLE)
    }

    fun getAllExerciseBluePrints(Id : String) : ArrayList<ExerciseBluePrint> {
        val cursor = repository.getAllById(
            Id,
            DataManager.EXERCISE_BLUEPRINT_TABLE,
            DataManager.EXERCISE_BP_WORKOUT_BLUEPRINT_ID
        )

        val items = arrayListOf<ExerciseBluePrint>()

        with(cursor) {
            while (moveToNext()) {
                val exerciseId = getString(getColumnIndexOrThrow(DataManager.ID_ROW))
                val exerciseName = getString(getColumnIndexOrThrow(DataManager.EXERCISE_BP_NAME))
                val workoutId = getString(getColumnIndexOrThrow(DataManager.EXERCISE_BP_WORKOUT_BLUEPRINT_ID))
                val exerciseInfo = getString(getColumnIndexOrThrow(DataManager.EXERCISE_BP_INFO))
                val workoutType = getString(getColumnIndexOrThrow(DataManager.EXERCISE_BP_WORKOUT_TYPE))

                items.add(ExerciseBluePrint(exerciseId,
                                            exerciseName,
                                            workoutId,
                                            exerciseInfo,
                                            WorkoutType.valueOf(workoutType),
                                        null))
            }
        }

        return items
    }
}