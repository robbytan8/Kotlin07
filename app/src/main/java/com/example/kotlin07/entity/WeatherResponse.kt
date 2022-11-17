package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class WeatherResponse(
  @SerializedName("weather") val weathers: ArrayList<Weather>,
  @SerializedName("main") val mainWeather: MainWeather,
  @SerializedName("wind") val wind: Wind
)