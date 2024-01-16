package com.owch.owch.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.owch.owch.data.Notification
import com.owch.owch.navigation.NavItem
import com.owch.owch.ui.elements.ContainerWithToolbar
import kotlinx.coroutines.delay
import java.nio.file.WatchEvent
import java.util.Date

@Composable
fun NotificationScreen(navHostController: NavHostController) {

    val messages = remember { mutableStateListOf<Notification>() }
    messages.clear()
    messages.addAll(createTestNotifications())

    Column(
        modifier = Modifier.background(color = Color(0xFFF3F8FF))
    ) {
        ContainerWithToolbar(
            title = "Notifications",
            onBackIconCLick = { navHostController.navigate(NavItem.Home.screenRoute) }
        ) { }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Clear all",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.2.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF4041D7),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { messages.clear() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF3F8FF))
                .zIndex(1f)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(messages) {
                NotificationItem(it)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationItem(notification: Notification) {
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(newValue = notification)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                show = false
                true
            } else false
        },
        positionalThreshold = { 150.dp.toPx() }
    )

    AnimatedVisibility(show, exit = fadeOut(spring())) {
        SwipeToDismiss(
            state = dismissState,
            background = { DismissBackground(dismissState = dismissState) },
            dismissContent = {
                Row(
                    modifier = Modifier
                        .shadow(
                            elevation = 8.dp,
                            ambientColor = Color(0x14080F28),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = notification.title,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.2.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF131320),
                                textAlign = TextAlign.Center,
                            )
                        )
                        Text(
                            text = notification.message,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.2.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF131320),
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                }
            }
        )
    }

//    LaunchedEffect(show) {
//        if (!show) {
//            delay(800)
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color.Green
        DismissDirection.EndToStart -> Color.Red
        null -> Color.Transparent
    }

    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.EndToStart)
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete"
            )

        Spacer(modifier = Modifier)
        if (direction == DismissDirection.StartToEnd)
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "archive"
            )

    }
}

fun createTestNotifications(): List<Notification> {
    val testNotifications = mutableListOf<Notification>()

    for (i in 1..20) {
        val notification = Notification(
            id = i.toLong(),
            title = "Title $i",
            message = "Lorem ipsum dolor sit amet consectetur. Lorem ipsum dolor sit amet consectetur. Lorem ipsum dolor sit amet consectetur. Lorem ipsum dolor sit amet consectetur.",
            date = Date()
        )
        testNotifications.add(notification)
    }

    return testNotifications
}
