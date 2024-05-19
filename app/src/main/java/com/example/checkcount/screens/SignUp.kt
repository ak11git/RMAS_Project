package com.example.checkcount.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkcount.R
import com.example.checkcount.components.EmailInput
import com.example.checkcount.components.NormalText
import com.example.checkcount.components.Password2Input
import com.example.checkcount.components.PasswordInput
import com.example.checkcount.components.SignUpText
import com.example.checkcount.components.UsernameInput

@Composable
fun SignUp() {
    Surface (
        color = colorResource(id = R.color.gray),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray))
            .padding(28.dp)
    ) {
        Column {
            NormalText(value = stringResource(id = R.string.firstText))
            SignUpText(value = stringResource(id = R.string.signUpText))
            UsernameInput(stringResource(id = R.string.username))
            EmailInput(stringResource(id = R.string.email))
            PasswordInput(stringResource(id = R.string.password))
            Password2Input(stringResource(id = R.string.password2))
        }
    }
}

@Preview
@Composable
fun DefaultPreviewSignUp() {
    SignUp()
}