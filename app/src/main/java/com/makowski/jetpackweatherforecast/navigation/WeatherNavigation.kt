package com.makowski.jetpackweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.makowski.jetpackweatherforecast.screens.about.AboutScreen
import com.makowski.jetpackweatherforecast.screens.favorites.FavoritesScreen
import com.makowski.jetpackweatherforecast.screens.main.MainViewModel
import com.makowski.jetpackweatherforecast.screens.main.WeatherMainScreen
import com.makowski.jetpackweatherforecast.screens.search.SearchScreen
import com.makowski.jetpackweatherforecast.screens.settings.SettingsScreen
import com.makowski.jetpackweatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        // www.google.pl/cityname="miłosław"
        val route = WeatherScreens.MainScreen.name
        composable("$route/{cityname}", arguments = listOf(
            navArgument(name = "cityname"){
                type= NavType.StringType
            }
        )){ navBack ->
            navBack.arguments?.getString("cityname").let { cityname ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController, mainViewModel,city = cityname)
            }
        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoritesScreen(navController = navController)
        }
        composable(WeatherScreens.SettingScreen.name){
            SettingsScreen(navController = navController)
        }
    }
}