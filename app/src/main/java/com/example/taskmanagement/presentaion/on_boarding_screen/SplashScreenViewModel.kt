package com.example.taskmanagement.presentaion.on_boarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.task.GetDataFromDataStoreUseCase
import com.example.domain.task.SaveDataInDataStoreUseCase
import com.example.taskmanagement.navGraph.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val saveDataInDataStore: SaveDataInDataStoreUseCase,
    private val getDataFromDataStore: GetDataFromDataStoreUseCase
) : ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted
    var startDestination: String? = null

    init {
        getOnBoardingState()
    }

    private fun getOnBoardingState() {
        viewModelScope.launch {
            _onBoardingCompleted.value = getDataFromDataStore().stateIn(viewModelScope).value
            startDestination =
                if (_onBoardingCompleted.value) Screen.MainMenu.route else Screen.Splash.route

        }
    }

    fun saveOnBoardingState(showTipsPage: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveDataInDataStore(showTipsPage = showTipsPage)
        }
    }
}