package com.example.taskmanagement.presentaion.art

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.model.UIState
import com.example.taskmanagement.ui.theme.BrownPink
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.example.taskmanagement.viewmodel.exhibition.ExhibitionDetailViewModel

@Composable
fun ExhibitionDetailScreen(navController: NavHostController) {
    val viewModel: ExhibitionDetailViewModel = hiltViewModel()
    val exhibitions = viewModel.exhibition.value

    Column(
        Modifier.fillMaxSize()
    ) {
        when (exhibitions) {
            is UIState.Success -> {
                Text(text = exhibitions.responseData?.data?.title.toString())
            }
            is UIState.Loading -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = 6.dp,
                        backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor
                    ) {
                        CircularProgressIndicator(Modifier.padding(8.dp), color = BrownPink)
                    }
                }
            }
            is UIState.Error -> {

            }
            else -> {}
        }
    }
}