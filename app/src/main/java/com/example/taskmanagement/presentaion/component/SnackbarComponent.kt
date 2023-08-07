package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SnackbarComponent(snackState: SnackbarHostState) {
    SnackbarHost(
        modifier = Modifier
            .padding(20.dp),
        hostState = snackState
    ) { snackbarData ->
        Snackbar(backgroundColor = Color.Red) {
            Text(text = snackbarData.message)
        }
    }
}