package com.kalyan.jetpackcomposepractice.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

companion object {
    var retrofitservice: Apiservice? = null

    fun retrofitInstance(): Apiservice {
        if (retrofitservice == null) {
            retrofitservice = Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apiservice::class.java)
        }
        return retrofitservice!!
    }

    val apiService: Apiservice by lazy {
        retrofitInstance()
    }

}


}