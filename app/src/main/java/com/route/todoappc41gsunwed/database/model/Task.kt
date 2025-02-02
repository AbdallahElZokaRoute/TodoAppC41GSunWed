package com.route.todoappc41gsunwed.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String? = null,
    val date: Date? = null,
    val isDone: Boolean? = false,
)
