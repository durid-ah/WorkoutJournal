package com.durid.workoutjournal.ui.workoutBlueprints

import android.content.Context
import androidx.lifecycle.*
import com.durid.workoutjournal.data.BluePrintRepo
import com.durid.workoutjournal.model.WorkoutBluePrint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class WorkoutBluePrintViewModel(private val dataSource : BluePrintRepo) : ViewModel() {

    fun addWorkoutBluePrint(bp : WorkoutBluePrint) {
          viewModelScope.launch {
            dataSource.addWorkoutBluePrint(bp)
            getWorkoutBluePrints()
        }
    }

    fun editWorkoutBluePrint(bp : WorkoutBluePrint){
        viewModelScope.launch {
            dataSource.editWorkoutBluePrint(bp)
            getWorkoutBluePrints()
        }
    }

    fun deleteWorkoutBluePrint(id : String) {
        viewModelScope.launch {
            dataSource.deleteWorkoutBluePrint(id)
            getWorkoutBluePrints()
        }
    }

    //updates the list of workout blueprints
    fun getWorkoutBluePrints() {
        viewModelScope.launch {
            val results = dataSource.getAllWorkoutBluePrints()
            workoutBluePrint.postValue(results)
        }

    }

    val workoutBluePrint = MutableLiveData<ArrayList<WorkoutBluePrint>>()
}

class WorkoutBluePrintViewModelFactory(private val dataSource: BluePrintRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkoutBluePrintViewModel(dataSource) as T
    }

}