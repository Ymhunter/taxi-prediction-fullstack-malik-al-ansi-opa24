package com.example.taxi_prediction_android.Utilities

import com.example.taxi_prediction_android.Commen.InputData
import com.example.taxi_prediction_android.Commen.PredictionResponse
import com.example.taxi_prediction_android.Commen.TaxiTrip
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaxiApi {
    @GET("taxi/")
    fun getTaxiData(): Call<List<TaxiTrip>>

    @POST("predict/")
    fun predict(@Body input: InputData): Call<PredictionResponse>
}
