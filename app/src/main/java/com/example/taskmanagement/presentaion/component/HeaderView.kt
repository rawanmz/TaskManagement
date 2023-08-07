package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmanagement.R
import com.example.taskmanagement.ui.theme.LightBlue

@Composable
fun HeaderView(backgroundImg:Int=R.drawable.ic_group,content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .background(color = LightBlue)
    ) {
        Image(
            painter = painterResource(backgroundImg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.None
        )
        content()
    }
}