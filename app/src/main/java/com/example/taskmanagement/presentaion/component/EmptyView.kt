package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.ui.theme.titleColor

@Composable
fun EmptyView(title: String?, message: String? = "", icon: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            title.orEmpty(),
            Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = MaterialTheme.colors.titleColor, fontSize = 20.sp)
        )

        Text(
            message.orEmpty(),
            Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = MaterialTheme.colors.titleColor, fontSize = 20.sp)
        )

        Image(
            modifier = Modifier.padding(vertical = 20.dp),
            painter = painterResource(id = icon),
            contentDescription = "" //stringResource(R.string.on_boarding_image)
        )
    }
}