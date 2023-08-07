package com.example.taskmanagement.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightPurple = getColor("#D9D7F1")
val LightWindow = getColor("#F1F1F1")
val LightGreen = getColor("#E7FBBE")
val LightPink = getColor("#FFCBCB")
val LightYellow = getColor("#FFFDDE")
val DarkWindow = getColor("#505050")
val LightGray = getColor("#F7F7F7")
val SkyBlue = getColor("#EAF9FE")
val LightBlue = getColor("#5AA6C8")

//fitness app color
val DarkGreen = getColor("#999B84")
val BrownPink = getColor("#926E6F")
val DarkPink = getColor("#92A3FD")
val LightPinkFitness = getColor("#E6C4C0")
val FitnessBackgroundColor = getColor("#F5E8C7")


fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}

val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if (isLight) LightWindow else DarkWindow

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.cardColor
    @Composable
    get() = if (isLight) White else Black








