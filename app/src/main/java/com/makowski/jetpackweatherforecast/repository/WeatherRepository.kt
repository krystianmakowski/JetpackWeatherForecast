package com.makowski.jetpackweatherforecast.repository

import com.makowski.jetpackweatherforecast.data.DataOrException
import com.makowski.jetpackweatherforecast.model.Weather
import com.makowski.jetpackweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){
    suspend fun getWeather(cityQuery: String): DataOrException<Weather,Boolean, Exception>{
        val response = try {
            api.getWeather(query = cityQuery)
        }catch (e:Exception){
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}