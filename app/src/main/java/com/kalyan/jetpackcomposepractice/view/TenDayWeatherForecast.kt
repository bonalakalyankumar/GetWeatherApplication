package com.kalyan.jetpackcomposepractice.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kalyan.jetpackcomposepractice.R
import com.kalyan.jetpackcomposepractice.data.TenDayWeatherForcastModel
import com.kalyan.jetpackcomposepractice.ViewModel.weatherViewmodel

@Composable
fun WeatherForecastDays(viewmodel: weatherViewmodel) {

    val tendayforecast by viewmodel.TenDayForecast.observeAsState()


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp,end=15.dp,top=15.dp)
        .height(255.dp)
        .background(color = Color.Black, shape= RoundedCornerShape(15.dp))
    ){
        Column (
            modifier = Modifier.padding(10.dp)

        ){

            Row(modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Image(
                    painter = painterResource(id= R.drawable.baseline_calendar_month_24)
                    , contentDescription = "calendar",
                    modifier = Modifier.size(19.dp)
                )
                Text(text = "7-day forecast", color = Color.White, fontSize = 17.sp)
            }
            tendayforecast?.let {
                data->
                LazyRow (
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp)
                ){
                    items(data.size) { index->
                        WeatherForecastDaysItem( tenday=data[index])
                    }

                }
            }
        }


    }


}

@Composable
fun WeatherForecastDaysItem(tenday: TenDayWeatherForcastModel){

    val customcolor= Color(0x25C28396)

    Box(modifier = Modifier.padding(3.dp)
        .height(200.dp)
        .width(70.dp)
        .background(customcolor, shape= RoundedCornerShape(55.dp))
    ){
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "${tenday.temperature}°", color = Color.White, fontSize = 20.sp)
            Spacer(modifier =  Modifier.height(5.dp))
            Text(text = "${tenday.FeelsLike}", color = Color.Gray, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(15.dp))
            AsyncImage(
                model = tenday.condition,
                contentDescription = "Cloudy",
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "${tenday.chanceOfRain}%",color= Color.Gray, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "${tenday.date.slice(0..2)}", color = Color.White, fontSize = 15.sp)
        }



    }
}
