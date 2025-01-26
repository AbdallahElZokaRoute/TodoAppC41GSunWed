package com.route.todoappc41gsunwed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.route.todoappc41gsunwed.database.dao.TasksDao
import com.route.todoappc41gsunwed.database.model.Task
import com.route.todoappc41gsunwed.database.typeConverters.Converters

@Database(arrayOf(Task::class), version = 1)
@TypeConverters(value = [Converters::class])
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    companion object {
        private var DATABASE_INSTANCE: TaskDatabase? = null
        fun init(applicationContext: Context) {
            if (DATABASE_INSTANCE == null)
                DATABASE_INSTANCE = Room.databaseBuilder(
                    applicationContext,
                    TaskDatabase::class.java,
                    "Tasks Database"
                ).allowMainThreadQueries() // Worker   Thread 1 <-> Thread 2 <-> Thread 3
                    .fallbackToDestructiveMigration()
                    .build()
        }

        fun getInstance(): TaskDatabase {
            return DATABASE_INSTANCE!!
        }
    }
}

