package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class MainWeather(
  @SerializedName("temp") val temperature: Double,
  @SerializedName("temp_min") val minimumTemperature: Double,
  @SerializedName("temp_max") val maximumTemperature: Double,
  @SerializedName("pressure") val pressure: Double,
  @SerializedName("humidity") val humidity: Double
)