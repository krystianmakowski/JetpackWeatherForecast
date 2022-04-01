package com.makowski.jetpackweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.makowski.jetpackweatherforecast.R
import com.makowski.jetpackweatherforecast.model.WeatherItem
import com.makowski.jetpackweatherforecast.utils.formatDate
import com.makowski.jetpackweatherforecast.utils.formatDateTime
import com.makowski.jetpackweatherforecast.utils.formatDecimals


@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(start = 15.dp))
            WeatherStateImage(imageUrl = imageUrl)
            Surface(shape = CircleShape, color = Color(0xFFFFC400)) {
                Text(weather.weather[0].description, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold)
                ){
                    append(formatDecimals(weather.temp.min) + "°")

                }
                withStyle(SpanStyle(color = Color.LightGray, fontWeight = FontWeight.SemiBold)) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
            })
        }
    }
}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Icon(painter = painterResource(id = R.drawable.sunrise), contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp))
            Text(text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Text(text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption)
            Icon(painter = painterResource(id = R.drawable.sunset), contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp))

        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity), contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp) )
            Text(text = "${weather.humidity}%",
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(painter = painterResource(id = R.drawable.pressure), contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp) )
            Text(text = "${weather.pressure}psi",
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(painter = painterResource(id = R.drawable.wind), contentDescription = "wind icon",
                modifier = Modifier.size(20.dp) )
            Text(text = "${weather.speed}kmh",
                style = MaterialTheme.typography.caption)
        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "icon image",
        modifier = Modifier.size(80.dp))

}
