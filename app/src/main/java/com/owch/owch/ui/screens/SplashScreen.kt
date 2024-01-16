package com.owch.owch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.owch.owch.navigation.NavItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    setValid: (Boolean) -> Unit,
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = null) {
        delay(3000L)
        navHostController.navigate(NavItem.Home.screenRoute)
        setValid(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.TwoTone.CheckCircle,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.Black),
            modifier = Modifier.size(100.dp)
        )
    }
}
