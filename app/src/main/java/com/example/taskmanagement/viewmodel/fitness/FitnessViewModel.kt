package com.example.taskmanagement.viewmodel.fitness

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.fitness.GetAllExercisesUseCase
import com.example.domain.fitness.GetCalculatedBMIUseCase
import com.example.domain.fitness.GetExerciseByIdUseCase
import com.example.model.UIState
import com.example.model.fitness.BMICalculator
import com.example.model.fitness.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitnessViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val getCalculatedBMIUseCase: GetCalculatedBMIUseCase,
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext val context: Context
) : ViewModel() {
    var allExercises: MutableState<UIState<List<Exercise>?>> = mutableStateOf(UIState.Loading())
    var exercises: MutableState<UIState<Exercise?>> = mutableStateOf(UIState.Loading())
    var bmi: MutableState<UIState<BMICalculator?>> = mutableStateOf(UIState.Loading())
    val age: MutableState<String> = mutableStateOf("")
    val weight: MutableState<String> = mutableStateOf("")
    var height: MutableState<String> = mutableStateOf("")

    init {
        getAllExercises()
        getExerciseByID()
    }

    private fun getAllExercises() {
        viewModelScope.launch {
            when (val response = getAllExercisesUseCase.invoke(context)) {
                is UIState.Success -> allExercises.value = UIState.Success(response.responseData)
                is UIState.Error -> allExercises.value = UIState.Error(response.error)
                is UIState.Empty -> allExercises.value =
                    UIState.Empty(title = response.title, iconRes = response.iconRes)

                else -> {}
            }
        }
    }

    private fun getExerciseByID() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id")
            when (val response = getExerciseByIdUseCase.invoke(id.orEmpty(), context = context)) {
                is UIState.Success -> exercises.value = UIState.Success(response.responseData)
                is UIState.Error -> exercises.value = UIState.Error(response.error)
                else -> {}
            }
        }
    }

    fun getCalculatedBMI() {
        viewModelScope.launch {
            when (val response =
                getCalculatedBMIUseCase.invoke(
                    age.value.toInt(),
                    weight.value.toDouble(),
                    height.value.toDouble()
                )) {
                is UIState.Success -> bmi.value = UIState.Success(response.responseData)
                is UIState.Error -> bmi.value = UIState.Error(response.error)
                is UIState.Empty -> bmi.value = UIState.Empty(response.message)

                else -> {}
            }
        }
    }

    fun validateBMIInput(): Boolean {
        val weightRange = (weight.value.toDouble() in 40.0..160.0)
        val heightRange = (height.value.toDouble() in 130.0..230.0)

        return (age.value.isEmpty()
                || weight.value.isEmpty()
                || height.value.isEmpty() || weightRange || heightRange)
    }
}