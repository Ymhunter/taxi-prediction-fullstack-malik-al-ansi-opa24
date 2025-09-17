package com.example.taxi_prediction_android

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taxi_prediction_android.Utilities.ApiClient
import com.example.taxi_prediction_android.Commen.InputData
import com.example.taxi_prediction_android.Commen.PredictionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputDistance = findViewById<EditText>(R.id.inputDistance)
        val inputPassengers = findViewById<EditText>(R.id.inputPassengers)
        val inputDuration = findViewById<EditText>(R.id.inputDuration)
        val spinnerTimeOfDay = findViewById<Spinner>(R.id.spinnerTimeOfDay)
        val spinnerDayOfWeek = findViewById<Spinner>(R.id.spinnerDayOfWeek)
        val spinnerTraffic = findViewById<Spinner>(R.id.spinnerTraffic)
        val spinnerWeather = findViewById<Spinner>(R.id.spinnerWeather)
        val predictBtn = findViewById<Button>(R.id.predictBtn)
        val resultText = findViewById<TextView>(R.id.resultText)

        spinnerTimeOfDay.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Morning", "Afternoon", "Evening", "Night")
        )
        spinnerDayOfWeek.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        )
        spinnerTraffic.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Low", "Medium", "High")
        )
        spinnerWeather.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Clear", "Rainy", "Snowy")
        )

        predictBtn.setOnClickListener {
            try {
                val distance = inputDistance.text.toString().toDoubleOrNull()
                val passengers = inputPassengers.text.toString().toDoubleOrNull()
                val duration = inputDuration.text.toString().toDoubleOrNull()

                if (distance == null || passengers == null || duration == null) {
                    resultText.text = "enter valid numbers."
                    return@setOnClickListener
                }

                val baseFare = 3.5
                val perKmRate = 1.2
                val perMinuteRate = 0.5

                val timeOfDay = listOf("Morning", "Afternoon", "Evening", "Night")
                val timeOfDayOneHot = timeOfDay.map { if (it == spinnerTimeOfDay.selectedItem) 1.0 else 0.0 }

                val dayTypeOneHot = when (spinnerDayOfWeek.selectedItem) {
                    "Saturday", "Sunday" -> listOf(0.0, 1.0)
                    else -> listOf(1.0, 0.0)
                }

                val traffic = listOf("Low", "Medium", "High")
                val trafficOneHot = traffic.map { if (it == spinnerTraffic.selectedItem) 1.0 else 0.0 }

                val weather = listOf("Clear", "Rainy", "Snowy")
                val weatherOneHot = weather.map { if (it == spinnerWeather.selectedItem) 1.0 else 0.0 }

                val features = listOf(
                    distance,
                    passengers,
                    duration,
                    baseFare,
                    perKmRate,
                    perMinuteRate
                ) + timeOfDayOneHot + dayTypeOneHot + trafficOneHot + weatherOneHot

                println("Android feature vector size: ${features.size}")

                val input = InputData(features = listOf(features))

                ApiClient.taxiApi.predict(input).enqueue(object : Callback<PredictionResponse> {
                    override fun onResponse(
                        call: Call<PredictionResponse>,
                        response: Response<PredictionResponse>
                    ) {
                        if (response.isSuccessful) {
                            val prediction = response.body()?.predictions?.getOrNull(0)
                            resultText.text = if (prediction != null) {
                                "💰 Predicted Fare: $%.2f".format(prediction)
                            } else {
                                "no prediction returned"
                            }
                        } else {
                            resultText.text = "error: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                        resultText.text = "failure: ${t.message}"
                    }
                })
            } catch (e: Exception) {
                resultText.text = "invalid input: ${e.message}"
            }
        }
    }
}
