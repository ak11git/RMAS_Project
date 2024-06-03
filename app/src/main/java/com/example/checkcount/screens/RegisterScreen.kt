package com.example.checkcount.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkcount.R
import com.example.checkcount.navigation.Nav
import com.example.checkcount.navigation.Screen

@Composable
fun Register(
    //onSignUpClick: () -> Unit,
    //onLoginClick: () -> Unit,
    //onPolicyClick: () -> Unit,
    //onPrivacyClick: () -> Unit,
) {

    val (agree, onAgreeChange) = rememberSaveable { mutableStateOf(false) }
    Surface(
        color = colorResource(id = R.color.lightBlue),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lightBlue))
            .padding(28.dp)
    ) {

        val nameValue = remember { mutableStateOf("") }
        val nameValue1 = remember { mutableStateOf("") }
        val nameValue2 = remember { mutableStateOf("") }
        val passwordValue = remember { mutableStateOf("") }
        val nameValue3 = remember { mutableStateOf("") }
        val checkedState = remember { mutableStateOf(false) }
        val (isImageSelected, setImageSelected) = remember { mutableStateOf(false) }

        var isFormValid = remember(nameValue, nameValue1, nameValue2, passwordValue, nameValue3, agree, isImageSelected) {
            nameValue.value.isNotBlank() && nameValue1.value.isNotBlank() && nameValue2.value.isNotBlank() &&
                    passwordValue.value.isNotBlank() && nameValue3.value.isNotBlank() && agree && isImageSelected
        }

        fun updateFormValidity() {
            val isNameValid = nameValue.value.isNotBlank()
            val isSurnameValid = nameValue1.value.isNotBlank()
            val isUsernameValid = nameValue2.value.isNotBlank()
            val isPasswordValid = passwordValue.value.isNotBlank()
            val isNumberValid = nameValue3.value.isNotBlank()
            isFormValid = isNameValid && isSurnameValid && isUsernameValid
                    && isPasswordValid && isNumberValid && agree && isImageSelected

            onAgreeChange(isFormValid)
        }

        fun Bitmap.rotate(degrees: Float): Bitmap {
            val matrix = Matrix().apply { postRotate(degrees) }
            return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        )
        {
            Text(
                text = stringResource(id = R.string.firstText),
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
            //
            Text(
                text = stringResource(id = R.string.signUpText),
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

            //NameInput(stringResource(id = R.string.name))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                label = { Text(text = stringResource(id = R.string.name)) },
                value = nameValue.value,
                onValueChange = { nameValue.value = it
                    updateFormValidity()},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = ""
                    )
                },
                maxLines = 1
            )

            //SurnameInput(stringResource(id = R.string.surname))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                label = { Text(text = stringResource(id = R.string.surname)) },
                value = nameValue1.value,
                onValueChange = { nameValue1.value = it
                    updateFormValidity()},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = ""
                    )
                },
                maxLines = 1
            )

            //UsernameInput(stringResource(id = R.string.username))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                label = { Text(text = stringResource(id = R.string.username)) },
                value = nameValue2.value,
                onValueChange = { nameValue2.value = it
                    updateFormValidity()},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = ""
                    )
                },
                maxLines = 1
            )
            //EmailInput(stringResource(id = R.string.email))

            //PasswordInput(stringResource(id = R.string.password))


            val passwordVisible = remember { mutableStateOf(false) }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                label = { Text(text = stringResource(id = R.string.password)) },
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it
                    updateFormValidity()},
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = ""
                    )
                },

                trailingIcon = {
                    val iconImg = if (passwordVisible.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    var desc = if (passwordVisible.value) {
                        stringResource(id = R.string.hidePassword)
                    } else {
                        stringResource(id = R.string.showPassword)
                    }

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = iconImg, contentDescription = desc)
                    }
                },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            //Password2Input(stringResource(id = R.string.password2))

            //NumberInput(stringResource(id = R.string.number))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                label = { Text(text = stringResource(id = R.string.number)) },
                value = nameValue3.value,
                onValueChange = { nameValue3.value = it
                    updateFormValidity()},
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_number),
                        contentDescription = ""
                    )
                }
            )

            //RequestContentPermission()
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val context = LocalContext.current
            val bitmap = remember {
                mutableStateOf<Bitmap?>(null)
            }

            val launcher = rememberLauncherForActivityResult(
                contract =
                ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
                setImageSelected(imageUri != null) //true ako je slika izabrana
            }
            Column {
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.width(170.dp).padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 6.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.darkBlue),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, colorResource(id = R.color.strongBlue))
                ) {
                    Text(text = "Pick image")
                }

                Spacer(modifier = Modifier.height(12.dp))

                imageUri?.let { uri ->
                    val contentResolver = context.contentResolver

                    val inputStream = contentResolver.openInputStream(uri)
                    val options = BitmapFactory.Options().apply {
                        inPreferredConfig = Bitmap.Config.ARGB_8888
                        inSampleSize = 1
                    }
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream?.close()

                    bitmap?.let { btm ->
                        // Rotiranje slike ako je potrebno
                        val exif = ExifInterface(contentResolver.openInputStream(uri)!!)
                        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                        val rotatedBitmap = when (orientation) {
                            ExifInterface.ORIENTATION_ROTATE_90 -> btm.rotate(90f)
                            ExifInterface.ORIENTATION_ROTATE_180 -> btm.rotate(180f)
                            ExifInterface.ORIENTATION_ROTATE_270 -> btm.rotate(270f)
                            else -> btm
                        }

                        // Prikaz slike
                        Image(
                            bitmap = rotatedBitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .border(
                                    width = 2.dp,
                                    color = Color.DarkGray,
                                )
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                Button(
                    onClick = {
                        imageUri = null // Postavi imageUri na null kada se pritisne dugme za uklanjanje slike
                        setImageSelected(false) // Postavi isImageSelected na false
                    },
                    enabled = isImageSelected,
                    modifier = Modifier
                        .width(170.dp)
                        .alpha(if (isImageSelected) 1f else 0.5f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 6.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.darkBlue),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, colorResource(id = R.color.strongBlue))
                ) {
                    Text(text = "Remove image")
                }

            }

            //CheckBox(stringResource(id = R.string.checkBox))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(56.dp)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                val privacyText = "Privacy"
                val policyText = "Policy"
                val annotatedString = buildAnnotatedString {
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("I Agree with")
                    }
                    append(" ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = privacyText, privacyText)
                        append(privacyText)
                    }
                    append(" And ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        pushStringAnnotation(tag = policyText, policyText)
                        append(policyText)
                    }
                }

                Checkbox(agree, onAgreeChange)
                ClickableText(
                    annotatedString,
                ) { offset ->
                    annotatedString.getStringAnnotations(offset, offset).forEach {
                        when (it.tag) {
                            privacyText -> {
                                Toast.makeText(context, "Privacy Text Clicked", Toast.LENGTH_SHORT)
                                    .show()
                                //onPrivacyClick()
                            }

                            policyText -> {
                                Toast.makeText(context, "Policy Text Clicked", Toast.LENGTH_SHORT)
                                    .show()
                                //onPolicyClick()
                            }
                        }
                    }
                }
            }

                //ButtonRegister(stringResource(id = R.string.buttonRegisterScreen))
                Button(
                    onClick = { Nav.goTo(Screen.MapScreen) },
                    enabled = isFormValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(if (isFormValid) 1f else 0.5f)
                        .heightIn(48.dp),
                    contentPadding = PaddingValues(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(48.dp)
                            .background(
                                brush = Brush.verticalGradient(listOf(colorResource(id = R.color.strongBlue)
                                                                    , colorResource(id = R.color.darkBlue))),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.buttonRegisterScreen),
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
}


@Preview
@Composable
fun DefaultPreviewRegister() {
    Register()
}