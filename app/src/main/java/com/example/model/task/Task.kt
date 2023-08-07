package com.example.model.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanagement.Constants.TASK_TABLE

@Entity(tableName = TASK_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String?,
    var description: String?,
    var taskDate: String?,
    var startTime: String?,
    var endTime: String?,
    var tagColor: String?,
    var category: String?
)