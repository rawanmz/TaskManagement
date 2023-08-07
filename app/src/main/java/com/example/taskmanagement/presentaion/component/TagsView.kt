package com.example.taskmanagement.presentaion.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.ui.theme.getColor

@Composable
fun TaskTag(labelColor: Color, tagColor: MutableState<String>) {
    val colorList =
        listOf("#FFCCCB", "#CCFFCD", "#5AA6C8", "#FFCBCB", "#D9D7F1", "#E7FBBE", "#FFFDDE")
    Text(
        "Select tag",
        Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp),
        fontWeight = FontWeight.Bold,
        style = TextStyle(color = labelColor, fontSize = 16.sp)
    )
    LazyRow(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemsIndexed(colorList, itemContent = { _, item ->
            Canvas(modifier = Modifier
                .padding(6.dp)
                .size(30.dp)
                .clickable {
                    tagColor.value = item
                },
                onDraw = {
                    drawCircle(color = getColor(item))
                })
        })
    }
}