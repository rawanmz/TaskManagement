package com.example.taskmanagement.presentaion.bmi

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.model.UIState
import com.example.taskmanagement.presentaion.component.ActionButton
import com.example.taskmanagement.presentaion.component.CustomCircularProgressIndicator
import com.example.taskmanagement.presentaion.component.SnackbarComponent
import com.example.taskmanagement.ui.theme.BrownPink
import com.example.taskmanagement.ui.theme.LightPink
import com.example.taskmanagement.ui.theme.LightYellow
import com.example.taskmanagement.viewmodel.fitness.FitnessViewModel
import kotlinx.coroutines.launch

@Composable
fun BMICalculatorScreen(navController: NavHostController) {
    val viewModel: FitnessViewModel = hiltViewModel()
    val context = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }
    SnackbarComponent(snackState)
    val bmi = viewModel.bmi.value
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        LightPink, LightYellow, LightPink,
                        LightYellow
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                "Age",
                Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = BrownPink, fontSize = 16.sp)
            )
            CustomCircularProgressIndicator(
                modifier = Modifier
                    .size(250.dp),
                initialValue = 25,
                primaryColor = BrownPink,
                secondaryColor = Color.Gray,
                circleRadius = 230f,
                minValue = 1,
                maxValue = 100,
                text = "years",
                onPositionChange = {
                    viewModel.age.value = it.toString()
                }
            )
            Text(
                "Weight",
                Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = BrownPink, fontSize = 16.sp)
            )
            CustomCircularProgressIndicator(
                modifier = Modifier
                    .size(250.dp),
                initialValue = 60,
                primaryColor = BrownPink,
                secondaryColor = Color.Gray,
                circleRadius = 230f,
                minValue = 0,
                text = "Kg",
                maxValue = 120,
                onPositionChange = {
                    viewModel.weight.value = it.toString()
                }
            )

            Text(
                "Height",
                Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = BrownPink, fontSize = 16.sp)
            )
            CustomCircularProgressIndicator(
                modifier = Modifier
                    .size(250.dp),
                initialValue = 170,
                primaryColor = BrownPink,
                secondaryColor = Color.Gray,
                circleRadius = 230f,
                minValue = 0,
                maxValue = 200,
                text = "Cm",
                onPositionChange = {
                    viewModel.height.value = it.toString()
                }
            )
            ActionButton("Calculate", BrownPink) {
                if (viewModel.weight.value.isNotEmpty() && viewModel.height.value.isNotEmpty()) {
                    Log.d("erorr button", viewModel.validateBMIInput().toString())

                    if (!viewModel.validateBMIInput()) {
                        Log.d("erorr state enter 2", "in button")
                        context.launch {
                            snackState.showSnackbar(
                                "weight must be between 40 kg to 160 kg \n height must be between 130 cm to 230 cm ",
                                duration = SnackbarDuration.Short
                            )
                        }
                    } else {
                        viewModel.getCalculatedBMI()
                    }
                }
            }
            if (bmi is UIState.Success) {
                Log.d("erorr succ", bmi.responseData.toString())
                Text(text = bmi.responseData?.data?.bmi.toString())
                Text(text = bmi.responseData?.data?.health.toString())
                Text(text = bmi.responseData?.data?.healthyBmiRange.toString())
            }
        }
    }
}
