package com.owch.owch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owch.owch.R
import com.owch.owch.ui.theme.OwchTheme

@Composable
fun CreateDefectScreen() {
    val selectedSeverityButton = remember { mutableStateOf(SeverityButton.UNDEFINED) }
    val selectedPriorityButton = remember { mutableStateOf(PriorityButton.UNDEFINED) }
    var comment by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home_indicator),
                contentDescription = null,
                tint = Color(0xFF9C9B9B)
            )
        }

        Text(
            text = "Defect report",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF090D10)
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Air Lines2",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF090D10)
            ),
            modifier = Modifier.padding(top = 24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(150.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF38ACE7),
                    shape = RoundedCornerShape(size = 4.dp)
                )
        ) {
            Text(
                text = "Upload photo",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF38ACE7)
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                tint = Color(0xFF38ACE7)
            )
        }

        //Severity block
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Severity",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF090D10)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SelectableButton(
                    title = SeverityButton.UNDEFINED.title,
                    selected = selectedSeverityButton.value == SeverityButton.UNDEFINED
                )

                SelectableButton(
                    title = SeverityButton.NO_CRITICAL.title,
                    selected = selectedSeverityButton.value == SeverityButton.NO_CRITICAL
                )

                SelectableButton(
                    title = SeverityButton.CRITICAL.title,
                    selected = selectedSeverityButton.value == SeverityButton.CRITICAL
                )
            }
        }

        //Priority block
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Priority",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF090D10)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SelectableButton(
                    title = PriorityButton.UNDEFINED.title,
                    selected = selectedPriorityButton.value == PriorityButton.UNDEFINED
                )

                SelectableButton(
                    title = PriorityButton.HIGH.title,
                    selected = selectedPriorityButton.value == PriorityButton.HIGH
                )

                SelectableButton(
                    title = PriorityButton.MEDIUM.title,
                    selected = selectedPriorityButton.value == PriorityButton.MEDIUM
                )

                SelectableButton(
                    title = PriorityButton.LOW.title,
                    selected = selectedPriorityButton.value == PriorityButton.LOW
                )
            }
        }

        //Comment block
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Comment",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF090D10)
                )
            )

            TextField(
                value = comment,
                onValueChange = { comment = it },
                placeholder = { Text(text = "Enter comment") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD63636),
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .fillMaxWidth()
                .height(42.dp)
                .background(color = Color(0xFFD63636), shape = RoundedCornerShape(size = 4.dp))
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Send defect report",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),

                    )
            )
        }
    }
}

@Composable
fun SelectableButton(title: String, selected: Boolean) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(20.dp)
            )
            .height(40.dp)
            .background(
                color = if (selected) Color(0xFFE9F4FA) else Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF090D10),
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateDefectScreenPreview() {
    OwchTheme {
        CreateDefectScreen()
    }
}

enum class SeverityButton(val title: String) {
    UNDEFINED("Undefined"),
    NO_CRITICAL("No critical"),
    CRITICAL("Critical")
}

enum class PriorityButton(val title: String) {
    UNDEFINED("Undefined"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low")
}