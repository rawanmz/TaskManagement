package com.example.model

import androidx.annotation.DrawableRes
import com.example.taskmanagement.R

sealed class TipsPage(
        @DrawableRes
        val image: Int,
        val title: String,
        val description: String
    ) {
    object First : TipsPage(
        image = R.drawable.time_table,
        title = "Time Table",
        description = "Add your daily tasks and manage your time."
    )

    object Second : TipsPage(
        image = R.drawable.finance,
        title = "Finance",
        description = "Keep track of your expenses and budget."
    )

    object Third : TipsPage(
        image = R.drawable.events,
        title = "Events and announcement",
        description = "Keeps people updated on the events they are interested in."
    )
}