package com.example.checkcount.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkcount.R
import com.example.checkcount.ui.theme.Primary
import com.example.checkcount.ui.theme.Secondary

@Composable
fun NormalText(value:String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SignUpText(value:String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun NameInput(labelValue:String) {
    val nameValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = nameValue.value,
        onValueChange = { nameValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_username),
                contentDescription = "")
        }
    )
}

@Composable
fun SurnameInput(labelValue:String) {
    val nameValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = nameValue.value,
        onValueChange = { nameValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_username),
                contentDescription = "")
        }
    )
}

@Composable
fun UsernameInput(labelValue:String) {
    val nameValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = nameValue.value,
        onValueChange = { nameValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_username),
                contentDescription = "")
        }
    )
}

/*@Composable
fun EmailInput(labelValue:String) {
    val emailValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = emailValue.value,
        onValueChange = { emailValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "")
        }
    )
}*/

@Composable
fun PasswordInput(labelValue:String) {
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = passwordValue.value,
        onValueChange = { passwordValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password),
                contentDescription = "")
        },

        /*trailingIcon = {
            val iconImg = if(passwordVisible.value) {
                Icons.Filled.Visibility
            }
            else {
                Icons.Filled.VisibilityOff
            }
        }*/
    )
}

/*@Composable
fun Password2Input(labelValue:String) {
    val passwordValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = passwordValue.value,
        onValueChange = { passwordValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password),
                contentDescription = "")
        }
    )
}*/

@Composable
fun NumberInput(labelValue:String) {
    val nameValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp)),
        label = { Text(text = labelValue) },
        value = nameValue.value,
        onValueChange = { nameValue.value = it },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_number),
                contentDescription = "")
        }
    )
}

@Composable
fun ButtonRegister(value: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)
    ) {
        Box(
            modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            /*Text(
                text = value,
                fontSize = 24.sp,
                color = Color.White,
                frontWeight = FontWeight.Bold
            )*/
        }

    }
}










