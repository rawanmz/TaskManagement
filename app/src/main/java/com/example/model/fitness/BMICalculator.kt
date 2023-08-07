package com.example.model.fitness

import com.google.gson.annotations.SerializedName

data class BMICalculator(
    @SerializedName("status_code") var statusCode: Int? = null,
    @SerializedName("request_result") var requestResult: String? = null,
    @SerializedName("data") var data: Data

)

data class Data(
    @SerializedName("bmi") var bmi: Double? = null,
    @SerializedName("health") var health: String? = null,
    @SerializedName("healthy_bmi_range") var healthyBmiRange: String? = null

)