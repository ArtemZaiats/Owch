package com.owch.owch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.owch.owch.navigation.NavItem
import com.owch.owch.ui.elements.ContainerWithToolbar

@Composable
fun DiaryScreen(navHostController: NavHostController) {
    Column {
        ContainerWithToolbar(
            title = "Diary",
            onBackIconCLick = { navHostController.navigate(NavItem.Home.screenRoute) }
        ) { }
    }
}