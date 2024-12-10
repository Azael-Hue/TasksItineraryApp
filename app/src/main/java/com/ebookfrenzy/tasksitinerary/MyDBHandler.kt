package com.example.tasksitinerary.com.ebookfrenzy.tasksitinerary

import android.content.Context
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/// modified from the SQLDemo and provider projects
class MyDBHandler(
    context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TASKS_TABLE = ("CREATE TABLE " +
                TABLE_TASKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TASK_NAME + " TEXT," +
                COLUMN_IMPORTANCE + " TEXT)")
        db.execSQL(CREATE_TASKS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS)
        onCreate(db)
    }

    companion object {
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "tasksDB.db"
        val TABLE_TASKS = "tasks"
        val COLUMN_ID = "_id"
        val COLUMN_TASK_NAME = "taskname"
        val COLUMN_IMPORTANCE = "importance"
    }

    fun addTask(task: Task) {
        val values = ContentValues()
        values.put(COLUMN_TASK_NAME, task.taskName)
        values.put(COLUMN_IMPORTANCE, task.taskImportance)
        val db = this.writableDatabase
        db.insert(TABLE_TASKS, null, values)
        db.close()
    }

    fun findTask(taskName: String): Task? {
        val query =
            "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_TASK_NAME = \"$taskName\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var task: Task? = null
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val importance = cursor.getString(2)
            task = Task(id, name, importance)
            cursor.close()
        }
        db.close()
        return task
    }

    fun deleteTask(taskName: String): Boolean {
        var result = false
        val query =
            "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_TASK_NAME = \"$taskName\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(
                TABLE_TASKS, COLUMN_ID + " = ?",
                arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    fun getAllTasks(): List<Task> {

        // a mutable list while ordered allows the adding anf removal of elements
        val taskList = mutableListOf<Task>()
        val query = "SELECT * FROM $TABLE_TASKS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val importance = cursor.getString(2)
                taskList.add(Task(id, name, importance))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return taskList
    }
}
