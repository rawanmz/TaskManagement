package com.example.taskmanagement.navGraph

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object TimeTable : Screen("time_table_screen")
    object AddTask : Screen("add_task_screen")
    object MainMenu : Screen("main_menu_screen")
    object UpdateTask : Screen("update_task_screen") {
        fun setArgument(taskId: Int): String {
            return "update_task_screen/$taskId"
        }
    }

    object FitnessList : Screen("fitness_list_screen")
    object ExersiceList : Screen("exercise_list_screen") {
        fun setArgument(bodyPart: String): String {
            return "exercise_list_screen/$bodyPart"
        }
    }

    object VideoScreen : Screen("video_screen") {
        fun setArgument(id: String): String {
            return "video_screen/$id"
        }
    }

    object BMIScreen : Screen("bmi_screen")
    object ArtScreen : Screen("art_screen")
    object ExhibitionScreen : Screen("exhibition_screen")
    object ExhibitionDetailScreen : Screen("exhibition_detail_screen") {
        fun setArgument(exhibitionId: Long): String {
            return "exhibition_detail_screen/$exhibitionId"
        }
    }
}
