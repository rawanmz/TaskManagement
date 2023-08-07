package com.example.taskmanagement

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.services.AudioService
import com.example.taskmanagement.navGraph.SetupNavGraph
import com.example.taskmanagement.presentaion.on_boarding_screen.SplashScreenViewModel
import com.example.taskmanagement.ui.theme.TaskManagementTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberNavController(bottomSheetNavigator)
            TaskManagementTheme {
                SetupNavGraph(navController)
                //StartService(context)
            }
        }
    }

    @Composable
    private fun StartService(context: Context) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Button(onClick = {
                val serviceIntent = Intent(context, AudioService::class.java)
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
                    println("if entred")
                    applicationContext.startForegroundService(serviceIntent)
                } else {
                    println("else entred")
                    applicationContext.startService(intent)
                }
            }) {
                Text(text = "start service")
            }
            Button(onClick = {
                val serviceIntent = Intent(context, AudioService::class.java)
                serviceIntent.action = "action_stop_my_services"
                context.stopService(serviceIntent)
            }) {
                Text(text = "stop service")
            }
        }
    }
}