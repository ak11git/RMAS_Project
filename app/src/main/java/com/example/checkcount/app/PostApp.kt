package com.example.checkcount.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.checkcount.navigation.Nav
import com.example.checkcount.navigation.Screen
import com.example.checkcount.screens.Login
import com.example.checkcount.screens.MapScreen
import com.example.checkcount.screens.RegOrLogin
import com.example.checkcount.screens.Register

@Composable
fun PostApp() {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = Nav.currentScreen) {
            currentState -> when(currentState.value){
                is Screen.RegOrLogScreen ->{
                    RegOrLogin()
                }
                is Screen.RegisterScreen ->{
                    Register()
                }
                is Screen.LoginScreen ->{
                    Login()
                }
                is Screen.MapScreen ->{
                    MapScreen()
                }
            }
        }
    }
}