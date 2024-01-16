package com.owch.owch.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owch.owch.data.InspectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OdometerCard(card: InspectCard) {
    var (textValue, onTextValue) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = card.cardTitle,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF090D10),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mileage",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF090D10),
                ),
        )

        TextField(
            value = textValue,
            onValueChange = { onTextValue(it) },
//            label = { Text(text = "Enter mileage") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(1.dp, Color(0xFF38ACE7), shape = RoundedCornerShape(8.dp))
                .background(color = Color.White),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFFE9F4FA)
            ),
//            shape = RoundedCornerShape(8.dp)
        )
    }
}