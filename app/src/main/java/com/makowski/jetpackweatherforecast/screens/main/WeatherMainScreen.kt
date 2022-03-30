package com.makowski.jetpackweatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.makowski.jetpackweatherforecast.data.DataOrException
import com.makowski.jetpackweatherforecast.model.Weather
import com.makowski.jetpackweatherforecast.widgets.WeatherAppBar

@Composable
fun WeatherMainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Miłosław")
    }.value
    if (weatherData.loading == true) CircularProgressIndicator()
    else if (weatherData.data != null) MainScaffold(weather = weatherData.data!!, navController)
}

@Composable
fun MainScaffold(weather: Weather,navController: NavController) {
    
    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
        elevation = 5.dp){
            //Buttton Clicked
        }

    }) {
        MainContent(data = weather)
        }
}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.city.name)

}
