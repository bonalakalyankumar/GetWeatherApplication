package com.kalyan.jetpackcomposepractice.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalyan.jetpackcomposepractice.data.Day
import com.kalyan.jetpackcomposepractice.data.RetrofitBuilder
import com.kalyan.jetpackcomposepractice.data.HourlyForecast
import com.kalyan.jetpackcomposepractice.data.TenDayWeatherForcastModel
import com.kalyan.jetpackcomposepractice.data.Weathermodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale



fun formatTime(time: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault()) // Extract only HH:mm
    return try {
        val date = inputFormat.parse(time)
        outputFormat.format(date ?: time)
    } catch (e: Exception) {
        time // Return original if parsing fails
    }
}
fun dayname(date: String): String {  // Change the return type to String
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parsedDate = formatter.parse(date)

    // Get the day of the week
    val calendar = Calendar.getInstance()
    calendar.time = parsedDate!!
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) ?: "Unknown"
}


class weatherViewmodel : ViewModel() {

    private val _weatherData = MutableLiveData<Weathermodel>()
    val weatherData: LiveData<Weathermodel> = _weatherData

    private val _hourlyForecast = MutableLiveData<List<HourlyForecast>>()  // Extracted Hourly Data
    val hourlyForecast: LiveData<List<HourlyForecast>> = _hourlyForecast

    private val _tenDayForecast =MutableLiveData<List<TenDayWeatherForcastModel>>()
    val TenDayForecast: LiveData<List<TenDayWeatherForcastModel>> = _tenDayForecast


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun getCurrentTime(): Calendar {
        return Calendar.getInstance()
    }

    fun fetchWeatherData(city: String, day: Int, aqi: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiservice = RetrofitBuilder.apiService
                val response = apiservice.getWeatherDataForecast(city, day, aqi)

                if (response.isSuccessful) {

                    val weatherResponse = response.body()
                    _weatherData.postValue(weatherResponse!!)


                    // Get the current time and calculate the next 24 hours using Calendar
                    val currentTime = getCurrentTime()
                    val endTime = Calendar.getInstance().apply {
                        timeInMillis = currentTime.timeInMillis
                        add(Calendar.HOUR_OF_DAY, 24)
                    }


                    val hourlyListToday = weatherResponse?.forecast?.forecastday?.firstOrNull()?.hour ?: emptyList()
                    val hourlyListTomorrow = weatherResponse?.forecast?.forecastday?.getOrNull(1)?.hour ?: emptyList()



                    val combinedHourlyList = hourlyListToday + hourlyListTomorrow

                    val hourlyList = combinedHourlyList.filter { data ->
                        val forecastTime = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).parse(data.time)
                        val forecastCalendar = Calendar.getInstance()
                        forecastCalendar.time = forecastTime

                        // Check if the forecast time is within the 24-hour window
                        forecastCalendar.after(currentTime) && forecastCalendar.before(endTime)
                    }.map { data ->
                        HourlyForecast(
                            time = formatTime( data.time),
                            temperature = data.temp_c.toInt(),
                            iconUrl = "https:${data.condition.icon}"
                        )
                    } ?: emptyList()

                    val tenDayList = weatherResponse?.forecast?.forecastday?.map { data ->

                        TenDayWeatherForcastModel(
                            temperature = data.day.maxtemp_c.toInt(),
                            FeelsLike =data.day.mintemp_c.toInt(),
                            condition = "https:${data.day.condition.icon}",
                            chanceOfRain = data.day.daily_chance_of_rain.toInt(),
                            date = dayname( data.date),
                            maxtemp_c = data.day.maxtemp_c.toInt(),
                            mintemp_c = data.day.mintemp_c.toInt()
                        )
                    } ?: emptyList()


                    _tenDayForecast.postValue(tenDayList)

                    _hourlyForecast.postValue(hourlyList)



                } else {
                    _error.postValue("Error: ${response.message()}")
                }

            } catch (e: IOException) {
                _error.postValue("Network Error: ${e.message}")
            } catch (e: HttpException) {
                _error.postValue("HTTP Error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }

}
