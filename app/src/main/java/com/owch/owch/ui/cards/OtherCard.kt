package com.owch.owch.ui.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owch.owch.data.InspectCard
import com.owch.owch.data.Title

@Composable
fun OtherCard(card: InspectCard) {
    val (selectedOption, onOptionSelected) = remember { mutableLongStateOf(0) }

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

        card.titles.forEach {
            Spacer(modifier = Modifier.height(8.dp))
            OtherItem(
                title = it,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }
    }
}


@Composable
fun OtherItem(
    title: Title,
    selectedOption: Long,
    onOptionSelected: (Long) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = (title.id == selectedOption),
                onClick = { onOptionSelected(title.id) }
            )
    ) {
        Text(
            text = title.title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

        RadioButton(
            selected = (title.id == selectedOption),
            onClick = null,
            modifier = Modifier.padding(10.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF38ACE7),
            )
        )
    }
}