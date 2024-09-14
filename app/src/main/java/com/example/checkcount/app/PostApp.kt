package com.example.checkcount.app

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.checkcount.location.LocationService
import com.example.checkcount.navigation.Router
import com.example.checkcount.viewModels.AuthViewModel
import com.example.checkcount.viewModels.ObjViewModel

@Composable
fun PostApp(
    viewModel: AuthViewModel,
    objViewModel: ObjViewModel
){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val isTrackingServiceEnabled = sharedPreferences.getBoolean("tracking_location", true)

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1
        )
    } else {
        if(isTrackingServiceEnabled) {
            Intent(context, LocationService::class.java).apply {
                action = LocationService.ACTION_FIND_NEARBY
                context.startForegroundService(this)
            }
        }else{
            Intent(context, LocationService::class.java).apply {
                action = LocationService.ACTION_START
                context.startForegroundService(this)
            }
        }

    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Router(viewModel, objViewModel)
    }
}