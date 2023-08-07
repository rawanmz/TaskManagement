package com.example.taskmanagement.presentaion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.model.task.Task
import com.example.model.UIState
import com.example.taskmanagement.presentaion.component.EmptyView
import com.example.taskmanagement.presentaion.time_table.CalendarView
import com.example.taskmanagement.presentaion.time_table.TimeLineViewList
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.example.taskmanagement.viewmodel.task.AddTaskViewModel

@Composable
fun TimeTable(navController: NavHostController) {
    val viewModel: AddTaskViewModel = hiltViewModel()
    val allTasks by viewModel.allTasks.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        CalendarView(navController, viewModel)
        when (allTasks) {
            is UIState.Success -> {
                TimeLineViewList((allTasks as UIState.Success<List<Task>>).responseData.orEmpty(), navController)
            }
            is UIState.Empty -> {
                (allTasks as UIState.Empty<List<Task>>).apply {
                    EmptyView(this.title, this.message, this.iconRes)
                }
            }
            else -> {}
        }
    }
}