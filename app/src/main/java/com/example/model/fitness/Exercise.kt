package com.example.model.fitness

import com.google.gson.annotations.SerializedName


data class Exercise(
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("videoUrl")
    val videoUrl: String,
    @SerializedName("id")
    var id: String
)