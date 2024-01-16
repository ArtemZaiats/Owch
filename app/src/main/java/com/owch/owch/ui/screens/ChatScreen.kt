package com.owch.owch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.owch.owch.ui.elements.ContainerWithToolbar
import com.owch.owch.ui.elements.ToolbarDropDownMenu

@Composable
fun ChatScreen(navigation: NavHostController) {
    val (isShowMenu, setShowMenu) = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F8FF)
            )
    ) {
        ContainerWithToolbar(
            title = "Chat Screen",
            onBackIconCLick = { navigation.popBackStack() },
            onMenuCLick = { setShowMenu(!isShowMenu) }
        )

        ToolbarDropDownMenu(expanded = isShowMenu, setExpanded = setShowMenu)
    }
}

