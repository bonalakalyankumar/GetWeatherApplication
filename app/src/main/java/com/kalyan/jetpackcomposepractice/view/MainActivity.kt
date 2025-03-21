package com.kalyan.jetpackcomposepractice.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.rememberPermissionState
import com.kalyan.jetpackcomposepractice.MyApp
import com.kalyan.jetpackcomposepractice.ViewModel.LocationViewModel
import com.kalyan.jetpackcomposepractice.R
import com.kalyan.jetpackcomposepractice.ViewModel.weatherViewmodel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                MyApp()
            }
        }
    }
}

@Composable
fun HomeScreen(latitude:Double?,longitude:Double?){
    val viewModel: weatherViewmodel = viewModel()
    val weatherData by viewModel.weatherData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeatherData(
            "$latitude,$longitude",
            14,
            "yes"
        ) // Example city, day, and air quality
    }


    weatherData?.let {
        val city: String = weatherData?.location?.name.toString()
        val climatedescription: String = weatherData?.current?.condition?.text.toString()
        val imageurl = "https:" + "${weatherData?.current?.condition?.icon}"
        val temperature = weatherData?.current?.temp_c?.toInt() ?: 0
        val feelslike = weatherData?.current?.feelslike_c?.toInt() ?: 0
        val humidity = weatherData?.current?.humidity?.toInt() ?: 0
        val wind = weatherData?.current?.wind_kph?.toInt() ?: 0
        val airQuality = weatherData?.current?.air_quality?.pm2_5?.toInt() ?: 0
        val uv = weatherData?.current?.uv?.toInt() ?: 0
        val winddir=weatherData?.current?.wind_dir.toString()

        val min_c =weatherData?.forecast?.forecastday?.get(0)?.day?.mintemp_c?.toInt() ?:0
        val max_c =weatherData?.forecast?.forecastday?.get(0)?.day?.maxtemp_c?.toInt() ?:0

        topSection(viewModel,city, climatedescription, temperature, feelslike,
            humidity, wind, imageurl,airQuality,uv,winddir,min_c,max_c)
    }
}

@Composable
fun topSection(viewModel: weatherViewmodel,city :String, climatedescription: String, temperature:Int,
               feelslike:Int, humidity:Int, wind:Int, imageurl:String,airquality:Int,uv:Int,winddir:String,max_c:Int,min_c:Int) {

    val painter = rememberImagePainter(data = imageurl)

    Box(
        modifier = Modifier.fillMaxSize()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF673AB7),
                        Color(0xFF000080),
                        Color(0xFF000080),
                        Color(0xFF000080),
                        Color(0xFF000080),
                        Color(0xFF03A9F4),
                        Color(0xFF03A9F4),
                        Color(0xFF03A9F4),
                        Color(0xFF03A9F4),
                        Color(0xFF03A9F4)

                    )
                )
            )

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Text(
                text = city,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 80.dp)
            )
            Text(
                text = climatedescription,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 30.dp)
            )
            Row {
                Text(
                    text = "$temperature",
                    fontSize = 120.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp, start = 30.dp)
                )
                Image(
                    painter = painter,
                    contentDescription = "cloudy",
                    modifier = Modifier.size(130.dp)
                        .padding(top = 10.dp)
                )
            }

            Text(
                text = "Feels  Like  $feelslike°",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "High $max_c° . Low $min_c°",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 10.dp)
            )

            WeatherData(viewModel)
            WeatherForecastDays(viewModel)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp) // Space between composables
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    AirQuality(airquality)
                }
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    UV(uv = uv)
                }
            }

            // Row 2: Humidity and Wind
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp) // Space between composables
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    Humidity(humidity)
                }
                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    Wind(wind, winddir)
                }
            }


        }
    }
}





