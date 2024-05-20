package com.example.checkcount.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object RegOrLogScreen : Screen()
    object SignUpScreen : Screen()
}

object Nav {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.RegOrLogScreen)

    fun goTo(destination : Screen) {
        currentScreen.value = destination;
    }
}