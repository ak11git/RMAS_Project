package com.example.checkcount.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkcount.R
import com.example.checkcount.components.ButtonRegister
import com.example.checkcount.components.CheckBox
import com.example.checkcount.components.NameInput
//import com.example.checkcount.components.EmailInput
import com.example.checkcount.components.NormalText
import com.example.checkcount.components.NumberInput
//import com.example.checkcount.components.Password2Input
import com.example.checkcount.components.PasswordInput
import com.example.checkcount.components.RequestContentPermission
import com.example.checkcount.components.SignUpText
import com.example.checkcount.components.SurnameInput
import com.example.checkcount.components.UsernameInput

@Composable
fun Register() {
    Surface (
        color = colorResource(id = R.color.gray),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray))
            .padding(28.dp)
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState()))
        {
            NormalText(value = stringResource(id = R.string.firstText))
            SignUpText(value = stringResource(id = R.string.signUpText))
            NameInput(stringResource(id = R.string.name))
            SurnameInput(stringResource(id = R.string.surname))
            UsernameInput(stringResource(id = R.string.username))
            //EmailInput(stringResource(id = R.string.email))
            PasswordInput(stringResource(id = R.string.password))
            //Password2Input(stringResource(id = R.string.password2))
            NumberInput(stringResource(id = R.string.number))
            RequestContentPermission()
            CheckBox(stringResource(id = R.string.checkBox))
            ButtonRegister(stringResource(id = R.string.buttonRegisterScreen))
        }
    }
}

@Preview
@Composable
fun DefaultPreviewSignUp() {
    Register()
}