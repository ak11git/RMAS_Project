package com.example.checkcount.screens.objs

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.checkcount.R
import com.example.checkcount.repository.Resource
import com.example.checkcount.screens.components.CustomCrowd
import com.example.checkcount.screens.components.CustomGalleryForAddNewObj
import com.example.checkcount.screens.components.CustomImageForNewObj
import com.example.checkcount.screens.components.customRichTextInput
import com.example.checkcount.screens.components.headingText
import com.example.checkcount.screens.components.inputTextIndicator
import com.example.checkcount.screens.components.loginRegisterCustomButton
import com.example.checkcount.viewModels.ObjViewModel
import com.google.android.gms.maps.model.LatLng


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddNewObjBottomSheet(
    objViewModel: ObjViewModel?,
    location: MutableState<LatLng?>,
    sheetState: ModalBottomSheetState
) {
    val objFlow = objViewModel?.objFlow?.collectAsState()
    val inputDescription = remember {
        mutableStateOf("")
    }
    val isDescriptionError = remember {
        mutableStateOf(false)
    }
    val descriptionError = remember {
        mutableStateOf("Ovo polje je obavezno")
    }
    val selectedOption = remember {
        mutableStateOf(0)
    }
    val buttonIsEnabled = remember {
        mutableStateOf(true)
    }
    val buttonIsLoading = remember {
        mutableStateOf(false)
    }

    val selectedImage = remember {
        mutableStateOf<Uri?>(Uri.EMPTY)
    }
    val selectedGallery = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val showedAlert = remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp, horizontal = 20.dp)
    ) {
        item{headingText(textValue = stringResource(id = R.string.add_new_obj_heading))}
        item{Spacer(modifier = Modifier.height(20.dp))}
        item{CustomImageForNewObj(selectedImageUri = selectedImage) }
        item{Spacer(modifier = Modifier.height(20.dp))}
        item{inputTextIndicator(textValue = "Description")}
        item{Spacer(modifier = Modifier.height(5.dp))}
        item{customRichTextInput(inputValue = inputDescription, inputText = "Enter a description", isError = isDescriptionError, errorText = descriptionError)}
        item{Spacer(modifier = Modifier.height(20.dp))}
        item{inputTextIndicator(textValue = "Crowd")}
        item{Spacer(modifier = Modifier.height(5.dp))}
        item{CustomCrowd(selectedOption)}
        item{Spacer(modifier = Modifier.height(20.dp))}
        item{inputTextIndicator(textValue = "Gallery")}
        item{Spacer(modifier = Modifier.height(5.dp))}
        item{CustomGalleryForAddNewObj(selectedImages = selectedGallery) }
        item{Spacer(modifier = Modifier.height(20.dp))}
        item{loginRegisterCustomButton(buttonText = "Add object", isEnabled = buttonIsEnabled, isLoading = buttonIsLoading) {
            showedAlert.value = false;
            buttonIsLoading.value = true
            objViewModel?.saveObjData(
                description = inputDescription.value,
                crowd = selectedOption.value,
                mainImage = selectedImage.value!!,
                galleryImages = selectedGallery.value,
                location = location
            )
        }}
        item{Spacer(modifier = Modifier.height(5.dp))}
//        Spacer(modifier = Modifier.height(100.dp))
    }
    objFlow?.value.let {
        when (it){
            is Resource.Failure -> {
                Log.d("Stanje flowa", it.toString());
                buttonIsLoading.value = false
                val context = LocalContext.current
                if(!showedAlert.value) {
//                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                    showedAlert.value = true
                    objViewModel?.getAllObjs()
                }else{}
            }
            is Resource.loading -> {

            }
            is Resource.Success -> {
                Log.d("Stanje flowa", it.toString());
                buttonIsLoading.value = false
                val context = LocalContext.current
                if(!showedAlert.value) {
//                    Toast.makeText(context, "Uspesno dodato", Toast.LENGTH_LONG).show()
                    showedAlert.value = true
                    objViewModel?.getAllObjs()
                }else{}
            }
            null -> {}
        }
    }
}
