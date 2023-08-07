package com.example.taskmanagement

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.ui.theme.BrownPink
import com.example.taskmanagement.ui.theme.LightPink


@SuppressLint("UnrememberedMutableState")
@Composable
fun ToolBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: Int? = com.example.taskmanagement.R.drawable.ic_back_button,
    barButtons: MutableList<TopBarActionButton> = mutableListOf(),
    onBackPressed: (() -> Unit)? = null,
    onActionButtonClicked: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth(),
                lineHeight = 20.sp,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                ),
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)){
                barButtons.forEach {
                    IconButton(
                        modifier = Modifier
                            .wrapContentSize(),
                        onClick = {
                            it.onClick()
                        },
                    ) {
                        Image(
                            painter = painterResource(id = it.mediaIcon),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxHeight(),
                        )
                    }
                }
            }
        },
        navigationIcon = {
            if (navigationIcon != null) {
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(35.dp),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        if (onBackPressed != null) {
                            onBackPressed()
                        }
                    }) {
                        Image(
                            painterResource(navigationIcon),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                }
            }
        },
        backgroundColor = Color.Transparent,//colorResource(id = R.drawable.gradient_primary_blue),
        elevation = 0.dp,
        modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    LightPink,
                    BrownPink
                )
            )
        )
    )
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

data class TopBarActionButton(val mediaIcon: Int, val onClick: () -> Unit)
