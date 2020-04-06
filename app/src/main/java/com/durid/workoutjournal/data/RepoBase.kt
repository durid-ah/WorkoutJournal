package com.durid.workoutjournal.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.durid.workoutjournal.model.WorkoutBluePrint

open class RepoBase(context: Context) {

   private val db : SQLiteDatabase
    var helper : DataManager? = null

    init {
        helper = DataManager(context)
        db = helper!!.writableDatabase
    }

    fun add(values : ContentValues, table : String) {
        db.insert(table, null, values)
    }

    fun edit(values:ContentValues, table: String, id : String) {
        val condition = "${DataManager.ID_ROW} = ?"
        val conditionArgs = arrayOf(id)

        db.update(table, values, condition, conditionArgs);
    }

    fun delete(id : String, table : String) {
        val condition = "${DataManager.ID_ROW} = ?"
        val conditionArgs = arrayOf(id)

        db.delete(table, condition, conditionArgs)
    }

    fun getAllById(Id : String, table: String, column : String) : Cursor {
        val condition = "$column = ?"
        val conditionArgs = arrayOf(Id)

        return db.query(
            table,
            null,
            condition,
            conditionArgs,
            null,
            null,
            null
        )
    }

    fun getAll(table: String) : Cursor {
        return db.query(table,
            null,
            null,
            null,
            null,
            null,
            null)
    }

}