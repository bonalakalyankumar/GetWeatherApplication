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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.kalyan.jetpackcomposepractice.R

@Composable
@Preview
fun Humidity(humidity:Int=0){
    val percentage=humidity
    val fillincolor= Color(0xC4382E52)
    Card (modifier = Modifier.padding(10.dp)
        .width(180.dp)
        .height(180.dp),
        shape= RoundedCornerShape(35.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
            // Background of the Card
        ) {
            Row (modifier = Modifier.padding(top = 10.dp, start = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Image(
                    painter = painterResource(R.drawable.baseline_water_drop_24),
                    contentDescription = "Humidity Icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Humidity", color = Color.White, fontSize = 20.sp)
            }
            Column(
                modifier = Modifier
                    .padding(16.dp,top=80.dp)
            ) {
                Text(
                    text = "$percentage%",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = Color.White
                )
            }

            // Inner Box to represent the filled portion based on the percentage
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((percentage / 100f) * 180.dp) // Height changes based on the percentage (e.g., 75% of 200dp)
                    .background( fillincolor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.BottomStart)// Color of the filled portion
            ) {


            }
        }

    }

}


@Composable
@Preview
fun Wind(wind:Int=0,winddir:String="") {

    val wind = wind
    val winddir=winddir.slice(0..0)

    val fillincolor = Color(0xC4382E52)

    Card(
        modifier = Modifier.padding(10.dp)
            .width(180.dp)
            .height(180.dp),
        shape = RoundedCornerShape(100.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Card(
            modifier = Modifier.padding(10.dp)
                .width(180.dp)
                .height(180.dp),
            shape = RoundedCornerShape(100.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = fillincolor
            )
        ) {
            Column(
                modifier = Modifier.padding(start = 40.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row() {
                    Image(
                        painter = painterResource(R.drawable.baseline_air_24),
                        contentDescription = "wind",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Wind",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
                Row() {

                    Text(text = "$wind", color = Color.White, fontSize = 45.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "km/h",
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
                Text(text = "From ${winddir}", color = Color.White, fontSize = 15.sp)
            }
        }
    }
}

@Composable
@Preview
fun AirQuality(airQuality: Int = 0) {
    val percentage = airQuality
    val fillincolor = Color(0xC4382E52)
    Card(
        modifier = Modifier.padding(10.dp)
            .width(180.dp)
            .height(180.dp),
        shape = RoundedCornerShape(35.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
            // Background of the Card
        ) {
            Row(
                modifier = Modifier.padding(top = 10.dp, start = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_waves_24),
                    contentDescription = "airQuality Icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Air Quality", color = Color.White, fontSize = 20.sp)
            }
            Column(
                modifier = Modifier
                    .padding(16.dp, top = 80.dp)
            ) {
                Text(
                    text = "$percentage",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = Color.White
                )
            }

            // Inner Box to represent the filled portion based on the percentage
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((percentage / 100f) * 180.dp) // Height changes based on the percentage (e.g., 75% of 200dp)
                    .background(
                        fillincolor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.BottomStart)// Color of the filled portion
            ) {


            }
        }

    }

}


@Composable
@Preview
fun UV(uv: Int = 0) {

    val uv = uv
    val fillincolor = Color(0xC4382E52)

    Card(
        modifier = Modifier.padding(10.dp)
            .width(180.dp)
            .height(180.dp),
        shape = RoundedCornerShape(100.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Card(
            modifier = Modifier.padding(10.dp)
                .width(180.dp)
                .height(180.dp),
            shape = RoundedCornerShape(100.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = fillincolor
            )
        ) {

            Column(
                modifier = Modifier.padding(start = 18.dp, top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row() {
                    Image(
                        painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                        contentDescription = "Uv",
                        modifier = Modifier.size(35.dp)
                            .padding(top = 15.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "UV Index",
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }
                Column() {

                    Text(text = "$uv", color = Color.White, fontSize = 45.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "Low", color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}