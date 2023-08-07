package com.example.taskmanagement.navGraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmanagement.presentaion.ArtWorksScreen
import com.example.taskmanagement.presentaion.MainMenuScreen
import com.example.taskmanagement.presentaion.SplashScreen
import com.example.taskmanagement.presentaion.TimeTable
import com.example.taskmanagement.presentaion.add_task.CalenderBottomSheet
import com.example.taskmanagement.presentaion.art.ExhibitionDetailScreen
import com.example.taskmanagement.presentaion.art.ExhibitionsScreen
import com.example.taskmanagement.presentaion.bmi.BMICalculatorScreen
import com.example.taskmanagement.presentaion.fitness_list.ExerciseList
import com.example.taskmanagement.presentaion.fitness_list.FitnessScreen
import com.example.taskmanagement.presentaion.on_boarding_screen.SplashScreenViewModel
import com.example.taskmanagement.presentaion.update_task.UpdateTask
import com.example.taskmanagement.presentaion.video.VideoPlayerScreen
import com.example.taskmanagement.viewmodel.fitness.FitnessViewModel
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    val splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(
            route = Screen.MainMenu.route
        ) {
            MainMenuScreen(navController = navController)
        }
        composable(route = Screen.TimeTable.route) {
            TimeTable(navController)
        }
        composable(route = Screen.AddTask.route) {
            CalenderBottomSheet(navController = navController)
        }
        composable(
            route = "${Screen.UpdateTask.route}/{taskId}",
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
            })
        ) {
            UpdateTask(navController = navController, it.arguments?.getInt("taskId"))
        }
        composable(route = Screen.FitnessList.route) {
            FitnessScreen(navController)
        }
        composable(
            route = "${Screen.ExersiceList.route}/{bodyPart}",
            arguments = listOf(navArgument("bodyPart") {
                type = NavType.StringType
            })
        ) {
            ExerciseList(
                navController = navController,
                it.arguments?.getString("bodyPart").orEmpty()
            )
        }
        composable(
            route = "${Screen.VideoScreen.route}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            //argument used in savedStateHandle in viewmodel
            val viewModel: FitnessViewModel = hiltViewModel()
            VideoPlayerScreen(navController, viewModel)
        }
        composable(route = Screen.BMIScreen.route) {
            BMICalculatorScreen(navController)
        }
        composable(route = Screen.ArtScreen.route) {
            ArtWorksScreen()
        }
        composable(route = Screen.ExhibitionScreen.route) {
            ExhibitionsScreen(navController)
        }
        composable(
            route = "${Screen.ExhibitionDetailScreen.route}/{exhibitionId}",
            arguments = listOf(navArgument("exhibitionId") {
                type = NavType.LongType
            })
        ) {
            ExhibitionDetailScreen(navController)
        }
    }
}