package com.example.checkcount.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
        //onMapLongClick:(LatLng)->Unit,
        //list: List<Poi>,
        //navigateToViewPoi: () -> Unit,
        //setSelectedPoi: (Poi) -> Unit
    ) {
    //val mapViewModel: MapViewModel by viewModels()


    val nis = LatLng(43.321445, 21.896104)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(nis, 20f);
        }


        var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
        var properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            //onMapLongClick = {latLng -> onMapLongClick(latLng)}
        ){
            //TODO: dodati marker za svaki poi iz liste list
            //tako da se klikom na Poi sadrzaj poi-a vidi na ViewPoi ekranu

            //list.forEach { poi ->
                Marker(
                    state = MarkerState(position = nis),
                    title = "Nis",
                    snippet = "Marker in Nis"
                )

            //}

        }
}


@Preview
@Composable
fun DefaultPreviewMapScreen() {
    MapScreen()
}
