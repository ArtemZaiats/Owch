package com.owch.owch.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreenWithMarkers() {
    val newYork = LatLng(40.7128, -74.0060)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newYork, 10f)
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            AdvancedMarker(
                state = MarkerState(position = newYork),
                title = "New York",
                snippet = "Marker in New York"
            )
            MarkerComposable(
                state = MarkerState(position = LatLng(40.8128, -74.2060)),
                title = "Second marker",
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "New York Marker",
                    tint = Color.Blue,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Switch(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp),
            checked = uiSettings.zoomControlsEnabled, onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                properties = if (it) {
                    properties.copy(mapType = MapType.NORMAL)
                } else {
                    properties.copy(mapType = MapType.SATELLITE)
                }
            })
    }
}