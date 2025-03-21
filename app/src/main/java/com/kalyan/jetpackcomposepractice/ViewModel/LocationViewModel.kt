package com.kalyan.jetpackcomposepractice.ViewModel

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices



class LocationViewModel(application: Application): AndroidViewModel(application) {

    private val _latitude = MutableLiveData<Double?>()
    val latitude:LiveData<Double?> get() = _latitude

    private val _longitude = MutableLiveData<Double?>()
    val longitude :LiveData<Double?> get()= _longitude

    val fusedLocationProviderClient =LocationServices.getFusedLocationProviderClient(application)


    fun fetchlocationsevice(){

        if(hasLocationPermission()){
            try {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        _latitude.value = location.latitude
                        _longitude.value = location.longitude
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }

    }

    private fun hasLocationPermission(): Boolean {
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            getApplication(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val coarseLocationPermission = ActivityCompat.checkSelfPermission(
            getApplication(),
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return fineLocationPermission == PackageManager.PERMISSION_GRANTED ||
                coarseLocationPermission == PackageManager.PERMISSION_GRANTED
    }
}
