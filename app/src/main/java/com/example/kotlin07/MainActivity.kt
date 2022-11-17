package com.example.kotlin07

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin07.adapter.CityDataAdapter
import com.example.kotlin07.adapter.CityDataAdapter.CityDataListener
import com.example.kotlin07.databinding.ActivityMainBinding
import com.example.kotlin07.entity.City
import com.example.kotlin07.entity.WeatherResponse
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

/**
 * @author Robby Tan
 */
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var cities: ArrayList<City>
  private lateinit var cityDataAdapter: CityDataAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    cities = ArrayList()
    cityDataAdapter = CityDataAdapter(cities)
    cityDataAdapter.setCityDataListener(object : CityDataListener {
      override fun cityItemClicked(city: City) {
        showWeatherData(city)
      }
    })

    binding.rvCities.layoutManager = LinearLayoutManager(this@MainActivity)
    binding.rvCities.adapter = cityDataAdapter
    binding.srLayout.setOnRefreshListener {
      this.fetchCityDataFromFile()
      binding.srLayout.isRefreshing = false
    }
  }

  override fun onStart() {
    super.onStart()
    this.fetchCityDataFromFile()
  }

  private fun fetchCityDataFromFile() {
    val inputStream = assets.open("city.list.json")
    val reader = JsonReader(InputStreamReader(inputStream, Charsets.UTF_8))
    val gson = Gson()
    val cities = gson.fromJson<Array<City>>(reader, Array<City>::class.java)
    this.cities.clear()
    this.cities.addAll(cities)
    this.cityDataAdapter.notifyItemChanged(0)
  }

  private fun showWeatherData(city: City) {
    val requestQueue = Volley.newRequestQueue(this@MainActivity)
    val uri = Uri.parse("https://api.openweathermap.org/data/2.5/weather").buildUpon()
      .appendQueryParameter("id", city.id.toString())
      .appendQueryParameter("appid", "value2")
      .build()
    val request = StringRequest(Request.Method.GET, uri.toString(),
      {
        val gson = Gson()
        val weatherResponse = gson.fromJson<WeatherResponse>(it, WeatherResponse::class.java)
        Toast.makeText(
          this@MainActivity,
          weatherResponse.mainWeather.humidity.toString(),
          Toast.LENGTH_SHORT
        ).show()
      },
      {
        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
      })
    requestQueue.add(request)
  }
}