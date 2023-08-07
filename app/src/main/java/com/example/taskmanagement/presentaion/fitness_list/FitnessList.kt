package com.example.taskmanagement.presentaion.fitness_list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.model.BodyPartsCards
import com.example.model.UIState
import com.example.model.fitness.Exercise
import com.example.taskmanagement.R
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.presentaion.component.EmptyView
import com.example.taskmanagement.ui.theme.*
import com.example.taskmanagement.viewmodel.fitness.FitnessViewModel

@Composable
fun FitnessScreen(navController: NavHostController) {
    Column() {
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            BrownPink,
                            LightPinkFitness,
                            FitnessBackgroundColor
                        )
                    ),
                    shape = AbsoluteRoundedCornerShape(
                        topLeftPercent = 30,
                        topRightPercent = 10,
                        bottomLeftPercent = 30,
                        bottomRightPercent = 30
                    )
                )
        ) {
            BmiCard(navController)
        }

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Area of focus",
            fontSize = 22.sp,
            color = MaterialTheme.colors.titleColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
        BodyPartsList(navController)
    }
}

@Composable
private fun BodyPartsList(navController: NavHostController) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(10.dp)
    ) {
        val bodyPartsList = listOf(
            BodyPartsCards(R.drawable.warmup, "Warm up"),
            BodyPartsCards(R.drawable.legs, "Legs"),
            BodyPartsCards(R.drawable.abs, "Abs"),
            BodyPartsCards(R.drawable.arms, "Arms"),
            BodyPartsCards(R.drawable.cardio, "Cardio")
        )
        items(bodyPartsList.size) { index ->
            Box(modifier = Modifier.padding(top = 20.dp)) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .background(BrownPink.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .clickable {
                            navController.navigate(Screen.ExersiceList.setArgument(bodyPartsList[index].title))
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = bodyPartsList[index].title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.titleColor,
                            textAlign = TextAlign.Center
                        )
                    )
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = bodyPartsList[index].image),
                        contentDescription = "" //stringResource(R.string.on_boarding_image)
                    )
                }
            }
        }
    }
}

@Composable
private fun BmiCard(navController: NavHostController) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.BMIScreen.route)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = buildAnnotatedString {
                append("BMI Calculator\n\n")
                addStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    start = 0,
                    end = 15
                )
                append("It's a good way to gauge whether your weight is in healthy proportion to your height.")
                addStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    ),
                    start = 15,
                    end = 100
                )
            },
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_exercise),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(10.dp),
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun ExerciseList(navController: NavHostController, bodyPart: String) {
    val viewModel: FitnessViewModel = hiltViewModel()
    when (val allExercises = viewModel.allExercises.value) {
        is UIState.Loading -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 6.dp,
                    backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor
                ) {
                    CircularProgressIndicator(Modifier.padding(8.dp), color = BrownPink)
                }
            }
        }
        is UIState.Success -> {
            LazyColumn(
                Modifier
                    .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
            ) {
                item {
                    Text(
                        "Start your training",
                        Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.titleColor
                    )
                }
                items(
                    items = (allExercises).responseData?.filter {
                        it.category == bodyPart
                    }.orEmpty(),
                    key = { exercise ->
                        exercise.id
                    }
                ) { exercise ->
                    ExerciseListItem(navController, exercise)
                }
            }
        }
        is UIState.Empty -> {
            EmptyView(allExercises.title, icon = allExercises.iconRes)
        }
        is UIState.Error -> {
            EmptyView(title = allExercises.error, icon = R.drawable.empty)

        }
    }
}

@Composable
private fun ExerciseListItem(
    navController: NavHostController,
    exercise: Exercise
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                navController.navigate(Screen.VideoScreen.setArgument(exercise.id))
            },
        elevation = 6.dp,
        border = BorderStroke(1.dp, BrownPink)
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Box(Modifier.size(120.dp)) {
                Canvas(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp),
                    onDraw = {
                        drawCircle(
                            LightPink.copy(0.2f)
                        )
                    })
                Icon(
                    Icons.Rounded.PlayArrow,
                    "",
                    Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    tint = BrownPink.copy(0.6f)
                )

            }
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
            ) {
                Text(
                    exercise.name,
                    Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.titleColor
                )
                Text(
                    exercise.description,
                    Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.titleColor
                )
                Row(
                    Modifier
                        .background(
                            color = BrownPink,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        exercise.duration,
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.titleColor
                    )
                }
            }
        }
    }
}