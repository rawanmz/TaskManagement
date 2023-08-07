package com.example.data.dao

import androidx.room.*
import com.example.model.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task_table WHERE id=:id")
    suspend fun deleteTask(id: Int)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE taskDate LIKE :date")
    fun sortByCreationDate(date: String): Flow<List<Task>>
}