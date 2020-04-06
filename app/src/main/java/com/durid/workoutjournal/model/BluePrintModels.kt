package com.durid.workoutjournal.model


data class WorkoutBluePrint(
    var Id : String?,
    var Name : String,
    var Description : String,
    var Exercises : ArrayList<ExerciseBluePrint>?
)

data class ExerciseBluePrint(
    var Id : String?,
    var ExerciseName : String,
    var WorkoutBluePrintId : String,
    var Info : String,
    var WorkoutType : WorkoutType,
    var Sets : ArrayList<ExerciseSet>?
)

data class ExerciseSet(
    var Id : String?,
    var ExerciseBluePrintId : String,
    var Amount : String,
    var Weight : Int,
    var WeightUnit : WeightUnit
)

enum class WorkoutType {
    REPS, TIMED
}

enum class WeightUnit {
    LBS, KG
}
