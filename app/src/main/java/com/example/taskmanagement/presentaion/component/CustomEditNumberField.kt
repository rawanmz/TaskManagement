package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor

@Composable
fun CustomEditNumberField(
    label: String,
    textColor: Color,
    value: MutableState<String>
) {
    Text(
        label,
        Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
        fontWeight = FontWeight.Bold,
        style = TextStyle(color = textColor, fontSize = 16.sp)
    )    // innerTextField that will display the Text hint
    TextField(modifier = Modifier
        .padding(horizontal = 6.dp)
        .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.titleColor,
            cursorColor = textColor,
            backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor,
            focusedIndicatorColor = textColor,
            focusedLabelColor = textColor,
        ),
        value = value.value,
        onValueChange = {
            value.value = it
        })
}