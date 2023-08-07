package com.example.data.remote

import com.example.model.fitness.Exercise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FitnessApi {
    @GET
    suspend fun getExerciseById(
        @Url url: String = "http://10.0.2.2:8080/get-exercise",
        @Query("id") id: String
    ): Response<Exercise>

    @GET
    suspend fun getAllExercises(
        @Url url: String = "http://10.0.2.2:8080/get-exercises"
    ): Response<List<Exercise>>

}