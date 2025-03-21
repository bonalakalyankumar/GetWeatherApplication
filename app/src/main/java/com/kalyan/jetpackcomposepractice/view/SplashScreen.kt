package com.kalyan.jetpackcomposepractice.view

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kalyan.jetpackcomposepractice.R
import com.kalyan.jetpackcomposepractice.ViewModel.LocationViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))

    val context = LocalContext.current
    val locationPermissionState = remember {
        mutableStateOf(false)
    }

    val locationViewModel: LocationViewModel = viewModel()

    val hasLocationPermission = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    if (hasLocationPermission) {
        locationViewModel.fetchlocationsevice()
    } else {
        locationPermissionState.value = true
    }


    val currentLatitude by locationViewModel.latitude.observeAsState()
    val currentLongitude by locationViewModel.longitude.observeAsState()


    if (locationPermissionState.value) {
        val permissionRequester = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                locationViewModel.fetchlocationsevice()
            } else {

            }
        }


        LaunchedEffect(Unit) {
            permissionRequester.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


    LaunchedEffect(currentLatitude, currentLongitude) {
        delay(3000) // Wait for 2 seconds
        currentLatitude?.let { lat ->
            currentLongitude?.let { lon ->
                navController.navigate("home/${lat}/${lon}")
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxSize()
                .padding(top = 350.dp),
            iterations = LottieConstants.IterateForever
        )
    }
}
