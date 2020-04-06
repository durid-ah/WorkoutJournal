package com.durid.workoutjournal.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.durid.workoutjournal.model.WorkoutBluePrint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class BluePrintRepo(context: Context) {
    private val repository : RepoBase = RepoBase(context);

    private fun createValueSet (bp : WorkoutBluePrint) : ContentValues {
        return ContentValues().apply {
            put(DataManager.ID_ROW, bp.Id)
            put(DataManager.WORKOUT_BP_NAME, bp.Name)
            put(DataManager.WORKOUT_BP_DESCRIPTION, bp.Description)
        }
    }

    suspend fun addWorkoutBluePrint(bp : WorkoutBluePrint) : String? = withContext(Dispatchers.IO) {
        bp.Id = UUID.randomUUID().toString()

        val values = createValueSet(bp)
        repository.add(values, DataManager.WORKOUT_BLUEPRINT_TABLE)

        bp.Id
    }

    suspend fun editWorkoutBluePrint(bp : WorkoutBluePrint) = withContext(Dispatchers.IO) {
        val values = createValueSet(bp)
        repository.edit(values, DataManager.WORKOUT_BLUEPRINT_TABLE, bp.Id!!)
    }

    suspend fun deleteWorkoutBluePrint(id : String) = withContext(Dispatchers.IO) {
        repository.delete(id, DataManager.WORKOUT_BLUEPRINT_TABLE)
    }

    suspend fun getAllWorkoutBluePrints() : ArrayList<WorkoutBluePrint> = withContext(Dispatchers.IO)  {
        val cursor = repository.getAll(DataManager.WORKOUT_BLUEPRINT_TABLE)

        val items = ArrayList<WorkoutBluePrint>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getString(getColumnIndexOrThrow(DataManager.ID_ROW))
                val itemName = getString(getColumnIndexOrThrow(DataManager.WORKOUT_BP_NAME))
                val itemDesc = getString(getColumnIndexOrThrow(DataManager.WORKOUT_BP_DESCRIPTION))

                items.add(WorkoutBluePrint(itemId, itemName, itemDesc, null))
            }
        }

        items
    }
}