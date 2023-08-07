package com.example.data.remote

import com.example.model.fitness.BMICalculator
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url


const val API_KEY = "0284a846e8msh795e64f25e783c4p14fffbjsnb9b8ff84841b"

interface FitnessCalculatorApi {
    @GET
    suspend fun getCalculatedBMI(
        @Url usr: String = "https://fitness-calculator.p.rapidapi.com/bmi",
        @Header("X-RapidAPI-Key") apiKey: String = API_KEY,
        @Query("age") age: Int,
        @Query("weight") weight: Double,
        @Query("height") height: Double
    ): Response<BMICalculator>

}