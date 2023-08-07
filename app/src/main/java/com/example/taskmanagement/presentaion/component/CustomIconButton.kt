package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(icon: ImageVector, onIconClick: () -> Unit) {
    IconButton(onClick = { onIconClick() }
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .background(Color.LightGray, (RoundedCornerShape(30.dp)))
                .padding(4.dp)
        ) {
            Icon(imageVector = icon, "")
        }
    }
}