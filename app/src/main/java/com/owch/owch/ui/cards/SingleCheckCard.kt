package com.owch.owch.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owch.owch.data.InspectCard
import com.owch.owch.ui.theme.OwchTheme

@Composable
fun SingleCheckCard(card: InspectCard) {
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

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD63636),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .width(95.dp)
                    .height(42.dp)
                    .background(color = Color(0xFFFFE5E5), shape = RoundedCornerShape(size = 4.dp))
            ) {
                Text(
                    text = "Fail",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFD63636),
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF07A355),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .width(95.dp)
                    .height(42.dp)
                    .background(color = Color(0xFFDDFDED), shape = RoundedCornerShape(size = 4.dp))
            ) {
                Text(
                    text = "Pass",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF07A355),
                    )
                )
            }
        }
    }
}

