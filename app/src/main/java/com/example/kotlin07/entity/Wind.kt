package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class Wind(
  @SerializedName("speed") val speed: Double,
  @SerializedName("deg") val degree: Double,
  @SerializedName("gust") val gust: Double
)