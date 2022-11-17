package com.example.kotlin07.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Robby Tan
 */
data class City(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String,
  @SerializedName("coord") val coordinate: Coordinate
)