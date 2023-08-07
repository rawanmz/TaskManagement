package com.example.taskmanagement.presentaion.time_table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.ui.theme.LightBlue
import com.example.taskmanagement.ui.theme.cardColor
import com.example.taskmanagement.viewmodel.task.AddTaskViewModel
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType

@Composable
fun CalendarView(navController: NavHostController, viewModel: AddTaskViewModel) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.cardColor)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top,
            ) {
                Button(
                    onClick = {
                       navController.navigate(Screen.AddTask.route)
                        //navController.navigate(Screen.UpdateTask.setArgument(id = viewModel.id.value))
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LightBlue,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Add task")
                }
            }
            Kalendar(
                kalendarType = KalendarType.Oceanic(),
                kalendarStyle = KalendarStyle(
                    kalendarBackgroundColor = MaterialTheme.colors.cardColor,
                    kalendarSelector = KalendarSelector.Rounded(
                        selectedColor = LightBlue,
                        todayColor = LightBlue
                    ),
                    kalendarColor = MaterialTheme.colors.cardColor,
                    elevation = 0.dp
                ),
                onCurrentDayClick = { day, _ ->
                    viewModel.sortTasksByDate(day.toString())
                },
                errorMessage = {
                    //Handle the error if any
                })
        }
    }
}