package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagement.ui.theme.LightBlue

@Composable
fun ActionButton(label: String, backgroundColor: Color = LightBlue, onClickAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.padding(12.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
            onClick = {
                onClickAction()
            }) {
            Text(label)
        }
    }
}