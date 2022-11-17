package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class Coordinate(
  @SerializedName("lon") val longitude: Double,
  @SerializedName("lat") val latitude: Double
)