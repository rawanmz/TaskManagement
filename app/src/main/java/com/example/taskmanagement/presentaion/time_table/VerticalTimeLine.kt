package com.example.taskmanagement.presentaion.time_table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.model.task.Task
import com.example.taskmanagement.R
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.ui.theme.LightBlue
import com.example.taskmanagement.ui.theme.getColor
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor


@Composable
fun TimeLineViewList(
    tasks: List<Task>,
    navController: NavHostController
) {
    LazyColumn {
        items(items = tasks, key = { task ->
            task.id
        }
        ) { task ->
            TimeLineViewItem(
                item = task,
                groupIndex = tasks.indexOf(task),
                groupSize = tasks.size,
                isHeader = true,
                content = {
                    if (task == tasks.first())
                        TaskCardFirstItem(task,navController)
                    else
                        TaskCard(task,navController)
                }
            )
        }
    }
}

@Composable
fun TimeLineViewItem(
    item: Task,
    groupSize: Int,
    groupIndex: Int,
    isHeader: Boolean,
    content: @Composable (Any) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(minHeight = 100.dp)
            .padding(horizontal = 16.dp)
    ) {
        val (circle, circleInnerLine, topLine, bottomLine, timeLineContent) = createRefs()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(25.dp)
                .background(shape = CircleShape, color = LightBlue)
                .constrainAs(circle) {
                    start.linkTo(parent.start)
                    top.linkTo(timeLineContent.top)
                    bottom.linkTo(timeLineContent.bottom)
                }) {
            Icon(
                painter = painterResource(R.drawable.ic_vector_circle),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)

            )
        }

        if (!isHeader) {
            Divider(
                modifier = Modifier.constrainAs(circleInnerLine) {
                    top.linkTo(circle.top)
                    bottom.linkTo(circle.bottom)
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    width = Dimension.value(2.dp)
                    height = Dimension.fillToConstraints
                },
                color = Color.Gray
            )
        }
        Surface(
            modifier = Modifier.constrainAs(timeLineContent) {
                start.linkTo(circle.end, 0.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            content(item)
        }
        if (groupIndex != 0) {
            Divider(
                modifier = Modifier.constrainAs(topLine) {
                    top.linkTo(parent.top)
                    bottom.linkTo(
                        circle.top,
                        if (isHeader) 1.dp else 0.dp
                    )
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    width = Dimension.value(2.dp)
                    height = Dimension.fillToConstraints
                },
                color = Color.Gray
            )
        }
        if (groupIndex != groupSize - 1) {
            Divider(
                modifier = Modifier.constrainAs(bottomLine) {
                    top.linkTo(
                        circle.bottom,
                        if (isHeader) 1.dp else 0.dp
                    )
                    bottom.linkTo(parent.bottom)
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    width = Dimension.value(2.dp)
                    height = Dimension.fillToConstraints
                },
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TaskCardFirstItem(item: Task, navController: NavHostController) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                navController.navigate(Screen.UpdateTask.setArgument(taskId = item.id))
            },
        elevation = 6.dp,
        border = BorderStroke(1.dp, LightBlue)
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = LightBlue.copy(0.1f))
        ) {
            Image(
                painter = painterResource(R.drawable.ic_group),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .matchParentSize(),
                contentScale = ContentScale.None
            )
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    item.title.orEmpty(),
                    Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.titleColor
                )
                Text(
                    item.description.orEmpty(),
                    fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Box(
                        Modifier.padding(vertical = 8.dp),

                        ) {
                        Text(
                            item.category.orEmpty(),
                            fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
                        )
                    }
                    Box(
                        Modifier
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = getColor(item.tagColor.orEmpty())
                            )
                            .padding(vertical = 8.dp, horizontal = 12.dp),

                        ) {
                        Text(
                            item.startTime.orEmpty().plus(" to ").plus(item.endTime),
                            fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(item: Task, navController: NavHostController) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                navController.navigate(Screen.UpdateTask.setArgument(taskId = item.id))
            },
        elevation = 6.dp,
        border = BorderStroke(1.dp, LightBlue)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                item.title.orEmpty(),
                Modifier.padding(bottom = 8.dp),
                fontWeight = FontWeight.Bold, color = MaterialTheme.colors.titleColor
            )
            Text(
                item.description.orEmpty(),
                fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Box(Modifier.padding(vertical = 8.dp)) {
                    Text(
                        item.category.orEmpty(),
                        fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
                    )
                }
                Box(
                    Modifier
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = getColor(item.tagColor.orEmpty())
                        )
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    Text(
                        item.startTime.orEmpty().plus(" to ").plus(item.endTime),
                        fontWeight = FontWeight.Normal, color = MaterialTheme.colors.titleColor
                    )
                }
            }
        }
    }
}