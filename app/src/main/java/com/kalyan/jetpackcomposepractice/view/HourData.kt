package com.kalyan.jetpackcomposepractice.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalyan.jetpackcomposepractice.R
import com.kalyan.jetpackcomposepractice.data.HourlyForecast
import com.kalyan.jetpackcomposepractice.ViewModel.weatherViewmodel

@Composable
fun WeatherData(viewModel: weatherViewmodel) {

    val hourlyForecast by viewModel.hourlyForecast.observeAsState()
    val error by viewModel.error.observeAsState()


    Box(
        modifier = Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
            .height(180.dp)
            .width(150.dp)
            .background(Color.Black, shape = RoundedCornerShape(15.dp))
            .padding(14.dp)// Padding inside the box
    ) {
        Column {
            Row(
                modifier = Modifier.padding(top = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "time",
                    modifier = Modifier.size(19.dp)
                )
                Text(text = "Hourly forecast", color = Color.White, fontSize = 17.sp)
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxSize()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            hourlyForecast?.let { hourlyForecast ->
                items(hourlyForecast.size){ forecast ->
                    WeatherForecastHourly(forecast = hourlyForecast[forecast])
                }
            }

        }
    }
}

@Composable
fun WeatherForecastHourly(forecast: HourlyForecast){
    Box(
        modifier = Modifier
            .padding(6.dp, top = 10.dp)
            .height(140.dp)
            .width(50.dp)
            .background(Color.Black,shape= RoundedCornerShape(55.dp))
    ){
        Column(
            modifier = Modifier.padding(6.dp)
                .padding(top=8.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "${forecast.temperature}°", color = Color.White, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = forecast.iconUrl,
                contentDescription ="cloudy",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = forecast.time, color = Color.White, fontSize = 13.sp)
        }
    }
}