package com.route.todoappc41gsunwed.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todoappc41gsunwed.database.model.Task
import java.util.Date

@Dao
interface TasksDao {
    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE date = :selectedDate")
    fun getTasksByDate(selectedDate: Date): List<Task>
}
