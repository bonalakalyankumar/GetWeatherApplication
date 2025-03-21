plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.kalyan.jetpackcomposepractice"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kalyan.jetpackcomposepractice"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit library for networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

// Converter for JSON to objects (Gson is a common choice)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Optional: OkHttp for logging network requests and responses (useful for debugging)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    // Coroutines core library (for background threading)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

// Coroutines Android library (for using coroutines in Android-specific components like UI updates)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Jetpack Compose dependencies
    implementation ("androidx.compose.ui:ui:1.1.0")
    implementation ("androidx.compose.material3:material3:1.0.0")

// LiveData support in Compose
    implementation ("androidx.compose.runtime:runtime-livedata:1.1.0")

// Lifecycle dependencies
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

        // Fused Location Provider
        implementation ("com.google.android.gms:play-services-location:18.0.0")

        // Google Play Services Core (Required for other location services)
        implementation ("com.google.android.gms:play-services-base:18.0.0")

        // Optional: for lifecycle-aware components
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")


        implementation("io.coil-kt:coil-compose:2.1.0")  // Use the latest version of Coil

        implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")// Example version
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") // Example version


        implementation ("com.airbnb.android:lottie-compose:5.0.0")


        implementation ("androidx.navigation:navigation-compose:2.4.0")


        implementation ("com.google.accompanist:accompanist-permissions:0.23.0")









}