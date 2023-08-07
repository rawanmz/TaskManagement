package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.remote.FitnessApi
import com.example.data.remote.FitnessCalculatorApi
import com.example.model.UIState
import com.example.model.fitness.BMICalculator
import com.example.model.fitness.Exercise
import com.example.taskmanagement.R
import com.google.gson.Gson
import javax.inject.Inject

class FitnessRepository @Inject constructor(
    private val fitnessApi: FitnessApi,
    private val fitnessCalculatorApi: FitnessCalculatorApi
) {
    suspend fun getExerciseById(id: String, context: Context): UIState<Exercise?> {
        val response = try {
            fitnessApi.getExerciseById(id = id)
        } catch (e: Exception) {
            val result = loadSuccessResponse(
                context,
                "exercises.json",
                Array<Exercise>::class.java
            ).firstOrNull { it.id == id }
            return UIState.Success(result)
        }
        return if (response.body() != null)
            UIState.Success(response.body())
        else UIState.Empty()
    }

    suspend fun getAllExercises(context: Context): UIState<List<Exercise>> {
        val response = try {
            fitnessApi.getAllExercises()
        } catch (e: Exception) {
            val result = loadSuccessResponse(context, "exercises.json", Array<Exercise>::class.java)
            return UIState.Success(result.toList())
        }
        if (response.body()?.isEmpty() == true) {
            return UIState.Empty(
                "There are no exercises.json available",
                iconRes = R.drawable.empty
            )
        }
        return UIState.Success(response.body().orEmpty())
    }

    suspend fun getCalculatedBMI(age: Int, weight: Double, height: Double): UIState<BMICalculator> {
        val response =
            fitnessCalculatorApi.getCalculatedBMI(age = age, weight = weight, height = height)
        try {
            Log.d("apiiii", response.isSuccessful.toString())
            Log.d("apiiii", response.message())
            Log.d("apiiii", response.raw().request.url.toString())
            //  if (response.isSuccessful) {
            return UIState.Success(response.body()!!)
            // }
        } catch (e: Exception) {
            Log.d("apiiii error", response.message())
            return UIState.Error(e.message.toString())
        }
    }

    private fun <T> loadSuccessResponse(
        context: Context,
        fileName: String,
        responseClass: Class<T>
    ): T {
        val jsonString = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        val res = Gson().fromJson(jsonString, responseClass)
        return res
    }
}