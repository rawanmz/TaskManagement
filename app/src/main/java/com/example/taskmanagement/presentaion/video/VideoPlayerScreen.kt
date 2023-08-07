package com.example.taskmanagement.presentaion.video

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.model.UIState
import com.example.taskmanagement.R
import com.example.taskmanagement.presentaion.component.EmptyView
import com.example.taskmanagement.presentaion.component.VideoPlayer
import com.example.taskmanagement.viewmodel.fitness.FitnessViewModel

@Composable
fun VideoPlayerScreen(navController: NavHostController, viewModel: FitnessViewModel) {
    val exercises = viewModel.exercises.value
    when (exercises) {
        is UIState.Success -> {
            VideoPlayer(exercises.responseData?.videoUrl.orEmpty())
        }
        is UIState.Error -> {
            EmptyView(exercises.error, icon = R.drawable.empty)
        }
        else -> {}
    }
}