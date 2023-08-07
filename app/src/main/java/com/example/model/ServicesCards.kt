package com.example.model

import androidx.annotation.DrawableRes
import com.example.taskmanagement.R

sealed class ServicesCards(
    @DrawableRes
    val image: Int,
    val title: String
) {
    object TimeTable : ServicesCards(
        image = R.drawable.time_table,
        title = "Time Table"
    )

    object Fitness : ServicesCards(
        image = R.drawable.yoga_green,
        title = "Fitness"
    )

    object Events : ServicesCards(
        image = R.drawable.events,
        title = "Events and announcement"
    )

    object Message : ServicesCards(
        image = R.drawable.messaging,
        title = "Message"
    )
}
data class BodyPartsCards(
    @DrawableRes
    val image: Int,
    val title: String
)