package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DateTextField(
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    value: MutableState<String>,
    color: Color
) {
    Text(
        "Task creation date",
        Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
        fontWeight = FontWeight.Bold,
        style = TextStyle(color = color, fontSize = 16.sp)
    )
    TextField(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.titleColor,
            cursorColor = color,
            backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor,
            focusedIndicatorColor = color,
            focusedLabelColor = color
        ),
        readOnly = true,
        value = value.value,
        onValueChange = {
            value.value = it
        },
        trailingIcon = {
            Icon(
                Icons.Filled.DateRange, "date",
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                },
            )
        },
    )
}