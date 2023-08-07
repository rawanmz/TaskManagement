package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.TaskDao
import com.example.model.task.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskManagementDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskManagementDatabase? = null

        fun getDatabase(context: Context): TaskManagementDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskManagementDatabase::class.java,
                    "task_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}