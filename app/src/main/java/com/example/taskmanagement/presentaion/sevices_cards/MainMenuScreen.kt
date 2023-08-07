package com.example.taskmanagement.presentaion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.model.ServicesCards
import com.example.taskmanagement.R
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.ui.theme.LightBlue
import com.example.taskmanagement.ui.theme.cardColor
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor

@Composable
fun MainMenuScreen(navController: NavController) {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightBlue)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_group),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.None
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp)
                    .background(
                        MaterialTheme.colors.welcomeScreenBackgroundColor,
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                contentPadding = PaddingValues(10.dp)
            ) {

                val servicesCardsList = listOf(
                    ServicesCards.TimeTable,
                    ServicesCards.Fitness,
                    ServicesCards.Message,
                    ServicesCards.Events
                )
                items(servicesCardsList.size) { index ->
                    Box(modifier = Modifier.padding(top = 20.dp)) {
                        MainMenuItem(servicesCardsList, index) {
                            when (index) {
                                0 -> {
                                    navController.navigate(Screen.TimeTable.route)
                                }
                                1 -> {
                                    navController.navigate(Screen.FitnessList.route)
                                }
                                2 -> {
                                    navController.navigate(Screen.ArtScreen.route)
                                }
                                3 -> {
                                    navController.navigate(Screen.ExhibitionScreen.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainMenuItem(
    servicesCardsList: List<ServicesCards>,
    index: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .aspectRatio(1f)
            .background(MaterialTheme.colors.cardColor, RoundedCornerShape(12.dp))
            .clickable {
                onClick.invoke()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(80.dp)
                .aspectRatio(1f),
            painter = painterResource(id = servicesCardsList[index].image),
            contentDescription = "" //stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = servicesCardsList[index].title,
            fontSize = 16.sp,
            color = MaterialTheme.colors.titleColor,
            textAlign = TextAlign.Center
        )
    }
}
//
//@Composable
//@Preview(showBackground = true)
//fun MainMenuScreenPreview() {
//    MainMenuScreen()
//}