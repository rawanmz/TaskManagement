package com.example.taskmanagement.presentaion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.model.UIState
import com.example.taskmanagement.presentaion.component.SnackbarComponent
import com.example.taskmanagement.ui.theme.BrownPink
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.example.taskmanagement.viewmodel.art.ArtWorksViewModel

@Composable
fun ArtWorksScreen() {
    val viewModel: ArtWorksViewModel = hiltViewModel()
    val artWorks = viewModel.artWork.value
    val context = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }
    SnackbarComponent(snackState)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (artWorks) {
            is UIState.Success -> {
                artWorks.responseData?.data?.forEach {
                    Text(text = it.title.orEmpty())
                }


            }
            is UIState.Empty -> {
                Text(text = artWorks.message.orEmpty())

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


            else -> {}
        }
    }
}