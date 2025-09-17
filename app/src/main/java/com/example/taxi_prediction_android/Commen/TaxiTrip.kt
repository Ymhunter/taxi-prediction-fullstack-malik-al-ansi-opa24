package com.example.taxi_prediction_android.Commen

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxiTrip(
    @Json(name = "Trip_Distance_km") val tripDistanceKm: Double,
    @Json(name = "Time_of_Day") val timeOfDay: String,
    @Json(name = "Day_of_Week") val dayOfWeek: String,
    @Json(name = "Passenger_Count") val passengerCount: Double,
    @Json(name = "Traffic_Conditions") val trafficConditions: String,
    @Json(name = "Weather") val weather: String,
    @Json(name = "Base_Fare") val baseFare: Double,
    @Json(name = "Per_Km_Rate") val perKmRate: Double,
    @Json(name = "Per_Minute_Rate") val perMinuteRate: Double,
    @Json(name = "Trip_Duration_Minutes") val tripDurationMinutes: Double,
    @Json(name = "Trip_Price") val tripPrice: Double?
)
