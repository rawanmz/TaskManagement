package com.example.taskmanagement.presentaion.component

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.taskmanagement.presentaion.add_task.TimePicker

@Composable
fun TimeView(
    context: Context,
    startTime: MutableState<String>,
    endTime: MutableState<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.weight(1f, fill = false)) {
            TimePicker("Start time", context, 0, 0, startTime)
        }
        Box(Modifier.weight(1f, fill = false)) {
            TimePicker("End time", context, 0, 0, endTime)
        }
    }
}