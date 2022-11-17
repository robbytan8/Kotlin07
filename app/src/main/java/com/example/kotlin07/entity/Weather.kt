package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class Weather(
  @SerializedName("id") val id: Int,
  @SerializedName("main") val main: String,
  @SerializedName("description") val description: String
)