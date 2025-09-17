package com.example.taxi_prediction_android.Commen

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class PredictionResponse(
    @Json(name = "predictions") val predictions: List<Double>
)