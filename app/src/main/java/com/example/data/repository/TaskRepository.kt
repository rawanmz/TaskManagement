package com.example.data.repository

import com.example.data.dao.TaskDao
import com.example.model.task.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun deleteTask(id: Int) {
        taskDao.deleteTask(id)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task = task)
    }

    fun sortByCreationDate(date: String): Flow<List<Task>> {
        return taskDao.sortByCreationDate(date)
    }
}