package com.owch.owch.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.owch.owch.R
import com.owch.owch.navigation.Screen
import com.owch.owch.ui.theme.OwchTheme
import com.owch.owch.utils.PreferencesManager
import com.owch.owch.viewModel.TokenViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: TokenViewModel) {
    val tokenResponse by viewModel.response.collectAsState()
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    viewModel.getUserToken("test@test.test", "testtest")
    Log.d("HomeScreen", "tokenResponse, access_token: ${tokenResponse?.access_token}")
    preferencesManager.saveToken("token", tokenResponse?.access_token ?: "")

    Column(
        modifier = Modifier
            .background(color = Color(0xFFF3F8FF))
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        HeaderContainer(navHostController)
        TopRecommendations()
        UpcomingAppointment()
        GradientButton()
        FAQButton()
    }
}

@Composable
fun HeaderContainer(navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFF7F9FE),
                        Color(0xFFE1E3FE),
                        Color(0xFFFAEAEC)
                    )
                ),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            )
            .padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .border(width = 2.dp, color = Color(0xFF4041D7), shape = CircleShape)
            )

            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { navHostController.navigate(Screen.DiaryScreen.route) }
                        .background(color = Color.White, shape = CircleShape)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_book),
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .height(40.dp)
                        .width(40.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { navHostController.navigate(Screen.NotificationScreen.route) }
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_notification_fill),
                        contentDescription = null,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "How may I be of service to you \ntoday?",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF131320),

                )
        )
    }
}

@Composable
fun TopRecommendations() {
    Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {
        Text(
            text = "Emergency assistance",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF080F28),
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFFFEADD), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 12.dp, top = 40.dp, end = 12.dp, bottom = 16.dp)
            ) {
                Column {
                    Icon(
                        painterResource(id = R.drawable.ic_attorney),
                        contentDescription = "image attorney",
                        tint = Color(0xFFE6925E)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF080F28),

                            ),
                        text = "Personal injury attorney"
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    Modifier
                        .align(Alignment.End)
                        .background(color = Color.White, shape = CircleShape)
                        .width(32.dp)
                        .height(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color(0xFFD3EAFF), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 12.dp, top = 40.dp, end = 12.dp, bottom = 16.dp)
            ) {
                Column {
                    Icon(
                        painterResource(id = R.drawable.ic_chiropractor),
                        contentDescription = "image attorney",
                        tint = Color(0xFF568BBC)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF080F28),

                            ),
                        text = "Physician Chiropractor care"
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .width(32.dp)
                        .height(32.dp)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun GradientButton() {
    var temp = remember { 1 }
    val refresh = remember { mutableStateOf(100) }

    Log.e("Home", "Load $temp ${refresh.value}")

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color(0x4D215CE7),
                shape = RoundedCornerShape(60.dp)
            )
            .shadow(
                elevation = 12.dp,
                ambientColor = Color(0xB2FFFFFF),
                shape = RoundedCornerShape(60.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF2463CB),
                        Color(0xFF215CE7),
                        Color(0xFF4987EF)
                    )
                ),
                shape = RoundedCornerShape(60.dp)
            )
            .clickable {
                temp++
                refresh.value++
                Log.e("Home", "Save $temp ${refresh.value}")
            }
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 14.dp, top = 8.dp, end = 14.dp, bottom = 8.dp),
        contentAlignment = Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ask for help",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF)
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Composable
fun FAQButton() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color(0x4D215CE7),
                shape = RoundedCornerShape(60.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(60.dp),
            )
            .clip(shape = RoundedCornerShape(60.dp))
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FAQ",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun UpcomingAppointment() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Upcoming appointment",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF131320),
                )
            )
            Text(
                text = "View all",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF4041D7)
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    ambientColor = Color(0x0D080F28),
                    shape = RoundedCornerShape(16.dp)
                )
                .shadow(
                    elevation = 8.dp,
                    ambientColor = Color(0x14080F28),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                ) {
                    Row {
                        Image(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFCFD9FF),
                                    shape = CircleShape
                                )
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Adam Letman Balley",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF131320)
                                )
                            )
                            Text(
                                text = "Attorney",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 18.2.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF78788E)
                                )
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = "image phone",
                        modifier = Modifier
                            .background(color = Color(0xFF4041D7), shape = CircleShape)
                            .width(40.dp)
                            .height(40.dp)
                            .padding(8.dp),
                        tint = Color.White,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(34.dp)
                        .background(
                            color = Color(0xFFDDE4FF),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Row {

                    }
                }
            }
        }
    }
}