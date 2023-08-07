package com.example.taskmanagement.viewmodel.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.TaskRepository
import com.example.model.UIState
import com.example.model.task.Task
import com.example.taskmanagement.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    // states
    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val taskDate: MutableState<String> = mutableStateOf("")
    val startTime: MutableState<String> = mutableStateOf("")
    val endTime: MutableState<String> = mutableStateOf("")
    val tagColor: MutableState<String> = mutableStateOf("")
    val category: MutableState<String> = mutableStateOf("")

    var allTasks: MutableStateFlow<UIState<List<Task>>> = MutableStateFlow(UIState.Loading())

    init {
        getAllTasks()
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task(
                id = id.value,
                title = title.value,
                description = description.value,
                taskDate = taskDate.value,
                startTime = startTime.value,
                endTime = endTime.value,
                tagColor = tagColor.value,
                category = category.value
            )
            taskRepository.insert(task)
        }
    }

    fun updateTask() {
        viewModelScope.launch {
            val task = Task(
                id = id.value,
                title = title.value,
                description = description.value,
                taskDate = taskDate.value,
                startTime = startTime.value,
                endTime = endTime.value,
                tagColor = tagColor.value,
                category = category.value
            )
            taskRepository.updateTask(task)
        }
    }

    private fun getAllTasks() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                taskRepository.getAllTasks().collect {
                    if (it.isNotEmpty()) {
                        allTasks.value = UIState.Success(it)
                    } else {
                        allTasks.value = UIState.Empty(
                            "No tasks",
                            "There are no tasks for today",
                            R.drawable.empty
                        )
                    }
                }
            }
        } catch (e: Exception) {
            allTasks.value = UIState.Error(e.message.toString())
        }
    }

    fun sortTasksByDate(date: String) {
        viewModelScope.launch {
            taskRepository.sortByCreationDate(date).map { tasks ->
                tasks.filter {
                    it.taskDate.toString() == date
                }
            }.collect {
                if (it.isNotEmpty())
                    allTasks.value = UIState.Success(it)
                else
                    allTasks.value = UIState.Empty(
                        "No tasks",
                        "There are no tasks for today",
                        R.drawable.empty
                    )
            }
        }
    }

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            taskRepository.getAllTasks().map { tasks ->
                tasks.firstOrNull {
                    it.id == taskId
                }
            }.collect {
                if (it != null) {
                    id.value = it.id
                    title.value = it.title.orEmpty()
                    description.value = it.description.orEmpty()
                    taskDate.value = it.taskDate.orEmpty()
                    startTime.value = it.startTime.orEmpty()
                    endTime.value = it.endTime.orEmpty()
                    tagColor.value = it.tagColor.orEmpty()
                    category.value = it.category.orEmpty()
                }
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(id)
        }
    }
}