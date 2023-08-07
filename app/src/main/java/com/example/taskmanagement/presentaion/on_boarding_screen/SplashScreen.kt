package com.example.taskmanagement.presentaion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.model.TipsPage
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.presentaion.on_boarding_screen.SplashScreenViewModel
import com.example.taskmanagement.ui.theme.LightPink
import com.example.taskmanagement.ui.theme.titleColor
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.google.accompanist.pager.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SplashScreen(
    navController: NavHostController,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    val pages = listOf(
        TipsPage.First,
        TipsPage.Second,
        TipsPage.Third
    )

    val onBoardingCompleted by splashScreenViewModel.onBoardingCompleted.collectAsState()

    val isCompleted = remember {
        mutableStateOf(false)
    }
    if (onBoardingCompleted) {
        navController.navigate(Screen.MainMenu.route)
    } else {
        isCompleted.value = true
    }
    if( isCompleted.value ) {
        val pagerState = rememberPagerState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
        ) {
            HorizontalPager(
                modifier = Modifier.weight(10f),
                state = pagerState,
                count = pages.size,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
            HorizontalPagerIndicator(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                pagerState = pagerState,
                activeColor = Color.Gray,
                inactiveColor = Color.LightGray,
                indicatorWidth = 12.dp,
                spacing = 8.dp
            )
            FinishButton(
                modifier = Modifier.weight(1f),
                pagerState = pagerState,
                pages = pages
            ) {
                navController.navigate(Screen.MainMenu.route)
                splashScreenViewModel.saveOnBoardingState(true)
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: TipsPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "" //stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    pages: List<TipsPage>,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == pages.lastIndex
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightPink,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = TipsPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = TipsPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = TipsPage.Third)
    }
}