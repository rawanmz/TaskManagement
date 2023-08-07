package com.example.taskmanagement.presentaion.add_task

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.R
import com.example.taskmanagement.ui.theme.LightBlue
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor

@Composable
fun TimePicker(
    label: String,
    context: Context, initHour: Int, initMinute: Int, value: MutableState<String>
) {
    val timePickerDialog = TimePickerDialog(
        context,
        R.style.TimePickerTheme,
        { _, hour: Int, minute: Int ->
            val AM_PM: String
            AM_PM = if (hour < 12) {
                "AM"
            } else {
                "PM"
            }

            value.value = "$hour:$minute $AM_PM"
        }, initHour, initMinute, false
    )
    Column() {
        Text(
            label,
            Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = LightBlue, fontSize = 16.sp)
        )
        TextField(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .wrapContentWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.titleColor,
                cursorColor = LightBlue,
                backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor,
                focusedIndicatorColor = LightBlue,
                focusedLabelColor = LightBlue
            ),
            readOnly = true,
            value = value.value,
            onValueChange = {
                value.value = it
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Notifications, "time",
                    modifier = Modifier.clickable {
                        timePickerDialog.show()
                    },
                )
            }
        )
    }
}