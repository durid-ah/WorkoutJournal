package com.durid.workoutjournal.ui.exerciseBluePrintEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.durid.workoutjournal.data.ExerciseBluePrintRepo
import com.durid.workoutjournal.data.ExerciseSetBluePrintRepo
import com.durid.workoutjournal.model.ExerciseBluePrint
import com.durid.workoutjournal.model.ExerciseSet
import kotlinx.coroutines.launch

class ExerciseBluePrintViewModel(
    private val dataSource : ExerciseBluePrintRepo,
    private val secondarySource : ExerciseSetBluePrintRepo
) : ViewModel() {

    fun getExerciseBluePrints(Id : String) {
        viewModelScope.launch {
            val results = dataSource.getAllExerciseBluePrints(Id)
            getSets(results)
            exerciseBluePrint.postValue(results)
        }
    }

    fun getSets(exercises : ArrayList<ExerciseBluePrint>) {
        for (ex in exercises) {
            ex.Sets = secondarySource.getAllExerciseSetBluePrints(ex.Id!!)
        }
    }

    fun addExerciseBluePrint(bp : ExerciseBluePrint) {
        viewModelScope.launch {
            dataSource.addExerciseBluePrint(bp)

            getExerciseBluePrints(bp.WorkoutBluePrintId)
        }
    }

    fun addExerciseSet(esBp : ExerciseSet) {
        viewModelScope.launch {
            secondarySource.addExerciseSetBluePrint(esBp)
        }
    }

    fun editExerciseBluePrint(bp : ExerciseBluePrint){
        viewModelScope.launch {
            dataSource.editExerciseBluePrint(bp)
            getExerciseBluePrints(bp.WorkoutBluePrintId)
        }
    }

    fun editExerciseSet(esBp: ExerciseSet) {
        viewModelScope.launch {
            secondarySource.editExerciseSetBluePrint(esBp)
        }
    }


    fun deleteWorkoutBluePrint(Id : String, workoutBpId : String) {
        viewModelScope.launch {
            dataSource.deleteExerciseBluePrint(Id)
            getExerciseBluePrints(workoutBpId)
        }
    }

    fun deleteExerciseSet(Id: String) {
        viewModelScope.launch {
            secondarySource.deleteExerciseSetBluePrint(Id)
        }
    }

    val exerciseBluePrint = MutableLiveData<ArrayList<ExerciseBluePrint>>().apply {
        value = ArrayList()
    }
}

class ExerciseBluePrintViewModelFactory(
    private val dataSource: ExerciseBluePrintRepo,
    private val secondarySource: ExerciseSetBluePrintRepo
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExerciseBluePrintViewModel(dataSource, secondarySource) as T
    }

}