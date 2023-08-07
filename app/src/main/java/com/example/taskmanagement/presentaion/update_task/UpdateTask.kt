package com.example.taskmanagement.presentaion.update_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.taskmanagement.presentaion.component.*
import com.example.taskmanagement.ui.theme.LightBlue
import com.example.taskmanagement.ui.theme.cardColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.example.taskmanagement.viewmodel.task.AddTaskViewModel
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpdateTask(navController: NavHostController, id: Int?) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val viewModel: AddTaskViewModel = hiltViewModel()
    if (id != null) {
        viewModel.getTaskById(id)
    }
    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(16.dp),
        sheetContent = {
            Kalendar(
                kalendarType = KalendarType.Firey(),
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
                    viewModel.taskDate.value = day.toString()
                },
                errorMessage = {
                    //Handle the error if any
                })
        }
    ) {
        HeaderView {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CustomIconButton(icon = Icons.Rounded.KeyboardArrowLeft) {
                    navController.popBackStack()
                }
                CustomIconButton(icon = Icons.Rounded.Delete) {
                    if (id != null) {
                        viewModel.deleteTask(id)
                    }
                    navController.popBackStack()
                }
            }
            UpdateTaskScreen(viewModel, modalBottomSheetState, navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun UpdateTaskScreen(
    viewModel: AddTaskViewModel,
    modalBottomSheetState: ModalBottomSheetState,
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackState = remember { SnackbarHostState() }
    SnackbarComponent(snackState)
    Column(
        Modifier
            .padding(top = 120.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                color = MaterialTheme.colors.welcomeScreenBackgroundColor,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        CustomTextField("Title", LightBlue, viewModel.title)
        CustomTextField("Description", LightBlue, viewModel.description)
        CustomTextField("Category", LightBlue, viewModel.category)
        DateTextField(coroutineScope, modalBottomSheetState, viewModel.taskDate, LightBlue)
        TimeView(context, viewModel.startTime,viewModel.endTime)
        TaskTag(LightBlue, viewModel.tagColor)
        ActionButton("Update task") {
            if (viewModel.title.value.isEmpty()
                or viewModel.description.value.isEmpty()
                or viewModel.category.value.isEmpty()
                or viewModel.taskDate.value.isEmpty()
                or viewModel.startTime.value.isEmpty()
                or viewModel.endTime.value.isEmpty()
                or viewModel.tagColor.value.isEmpty()
            ) {
                coroutineScope.launch {
                    snackState.showSnackbar(
                        "field must not be empty",
                        duration = SnackbarDuration.Short
                    )
                }
            } else {
                viewModel.updateTask()
                navController.popBackStack()
            }
        }
    }
}
