package com.example.checkcount.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkcount.R
import com.example.checkcount.components.ButtonLog
import com.example.checkcount.components.ButtonMap
import com.example.checkcount.components.ButtonReg


@Composable
fun RegOrLogin() {
    Surface (
        color = colorResource(id = R.color.lightBlue),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightBlue))
            .padding(28.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
        Row (modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            ButtonReg("Register")
            ButtonLog("Login")
        }
        //ButtonMap("Map")
    }
}

@Preview
@Composable
fun DefaultPreviewRegOrLogin() {
    RegOrLogin()
}