package com.durid.workoutjournal.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataManager(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object Constants {
        const val DB_NAME = "workout_journal_db"
        const val DB_VERSION = 1

        const val WORKOUT_BLUEPRINT_TABLE = "workout_blueprint"
        const val EXERCISE_BLUEPRINT_TABLE = "exercise_blueprint"
        const val EXERCISE_SET_BLUEPRINT_TABLE = "exercise_set_blueprint"

        const val ID_ROW = "_id"

        const val WORKOUT_BP_NAME = "name"
        const val WORKOUT_BP_DESCRIPTION = "description"

        const val EXERCISE_BP_NAME = "exercise_name"
        const val EXERCISE_BP_WORKOUT_BLUEPRINT_ID = "workout_blueprint_id"
        const val EXERCISE_BP_INFO = "info"
        const val EXERCISE_BP_WORKOUT_TYPE = "workout_type"

        const val EXERCISE_BP_ID = "exercise_blueprint_id"
        const val EXERCISE_SET_BP_AMOUNT = "amount"
        const val EXERCISE_SET_BP_WEIGHT = "weight"
        const val EXERCISE_SET_BP_WEIGHT_UNIT = "weight_unit"
    }

    override fun onCreate(db : SQLiteDatabase?) {
        val workoutBluePrintTable =
            ("create table $WORKOUT_BLUEPRINT_TABLE ("
                    + "$ID_ROW text primary key not null,"
                    + "$WORKOUT_BP_NAME text not null,"
                    + "$WORKOUT_BP_DESCRIPTION text);")

        db!!.execSQL(workoutBluePrintTable)

        val exerciseBluePrintTable =
            ("create table $EXERCISE_BLUEPRINT_TABLE ("
                    + "$ID_ROW text primary key not null, "
                    + "$EXERCISE_BP_WORKOUT_BLUEPRINT_ID text not null, "
                    + "$EXERCISE_BP_NAME text not null, "
                    + "$EXERCISE_BP_INFO text not null, "
                    + "$EXERCISE_BP_WORKOUT_TYPE text not null, "
                    + " FOREIGN KEY (${EXERCISE_BP_WORKOUT_BLUEPRINT_ID}) "
                    + "REFERENCES ${WORKOUT_BLUEPRINT_TABLE}(${ID_ROW}));")

        db.execSQL(exerciseBluePrintTable)

        val exerciseSetBluePrintTable =
            ("create table $EXERCISE_SET_BLUEPRINT_TABLE ("
                    + "$ID_ROW text primary key not null,"
                    + "$EXERCISE_BP_ID text not null,"
                    + "$EXERCISE_SET_BP_AMOUNT text not null,"
                    + "$EXERCISE_SET_BP_WEIGHT integer not null,"
                    + "$EXERCISE_SET_BP_WEIGHT_UNIT text not null," +
                    " FOREIGN KEY (${EXERCISE_BP_ID}) " +
                    "REFERENCES ${EXERCISE_BLUEPRINT_TABLE}(${ID_ROW}));")

        db.execSQL(exerciseSetBluePrintTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}