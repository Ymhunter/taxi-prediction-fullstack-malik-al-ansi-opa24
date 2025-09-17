package com.example.taxi_prediction_android.Commen
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InputData(
    val features: List<List<Double>>
)

