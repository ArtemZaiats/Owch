package com.owch.owch.ui.screens

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationScreen() {

    val newYork = LatLng(40.7128, -74.0060)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(36.7783, -119.4179), 6f)
    }

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true,
                compassEnabled = true,
                mapToolbarEnabled = true
            )
        )
    }

    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isMyLocationEnabled = true,
                isTrafficEnabled = true
            )
        )
    }


    val routeCoordinates = listOf(
        LatLng(37.7749, -122.4194), // Starting point (e.g., San Francisco)
        LatLng(36.7783, -119.4179), // Waypoint 1
        LatLng(34.0522, -118.2437), // Waypoint 2 (e.g., Los Angeles)
        LatLng(32.7157, -117.1611),  // Ending point (e.g., San Diego)
        LatLng(30.7157, -116.001),  // Ending point (e.g., San Diego)
    )

    var selectedRoute by remember { mutableStateOf<Route?>(null) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Polyline(
            points = routeCoordinates,
            clickable = true,
            color = Color.Blue,
            width = 5f,
            tag = CaliforniaRoute,
            onClick = { polyline ->
                selectedRoute = polyline.tag as? Route
            }
        )
        Marker(
            state = MarkerState(position = newYork)
        )
    }

    selectedRoute?.let {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.offset(y = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10))
                    .background(color = Color.DarkGray)
                    .padding(20.dp)
            ) {
                Text(text = it.name, style = TextStyle(fontSize = 20.sp, color = Color.White))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.description,
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }
        }
    }
}

@Composable
fun DeniedPermissionMessage(onRequestPermissionAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Location permission is required to use this feature.",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Button to request location permission again
            TextButton(onClick = onRequestPermissionAgain) {
                Text(text = "Request Permission Again")
            }
        }
    }
}

data class Route(val name: String, val description: String)

val CaliforniaRoute = Route(
    name = "California Road Trip",
    description = "Explore the beautiful coast of California on this scenic road trip from San Francisco to San Diego."
)


