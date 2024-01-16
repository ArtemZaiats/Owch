package com.owch.owch.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owch.owch.R
import com.owch.owch.data.InspectVehicle
import com.owch.owch.viewModel.VehicleViewModel
import androidx.navigation.NavHostController
import com.owch.owch.navigation.NavItem
import com.owch.owch.navigation.Screen
import com.owch.owch.ui.elements.ContainerWithToolbar
import com.owch.owch.utils.PreferencesManager

@Composable
fun InspectAssetScreen(navHostController: NavHostController, viewModel: VehicleViewModel) {

    val vehicles by viewModel.vehicles.collectAsState()
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val token = "Bearer ${preferencesManager.getToken("token", "")}"
    viewModel.fetchVehicles(token)
    val (selectedOption, onOptionSelected) = remember { mutableLongStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color(0xFFF3F8FF))
    ) {
        ContainerWithToolbar(
            title = "Inspect asset",
            onBackIconCLick = { navHostController.popBackStack() }
        ) { }

        if (selectedOption != 0L) {
            Button(
                onClick = {
                    navHostController.navigate(
                        Screen.FormListScreen.setVehicleId(
                            selectedOption
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(42.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF38ACE7),
                    disabledContainerColor = Color(0xFFD9D9D9),
                    contentColor = Color.White,
                    disabledContentColor = Color(0xFFD9D9D9)
                )
            ) {
                Text(
                    text = "Select asset",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
//                    color = Color.White,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            items(vehicles) { vehicle ->
                VehicleItem(
                    vehicle = vehicle,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun VehicleItem(
    vehicle: InspectVehicle,
    selectedOption: Long,
    onOptionSelected: (Long) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFE9E9E9),
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .selectable(
                selected = (vehicle.vehicle_id == selectedOption),
                onClick = { onOptionSelected(vehicle.vehicle_id) }
            )
            .padding(vertical = 8.dp)
    ) {
        RadioButton(
            selected = (vehicle.vehicle_id == selectedOption),
            onClick = null,
            modifier = Modifier.padding(10.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF38ACE7),
                unselectedColor = Color(0xFFA8A9AB)
            )
        )

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Icon(
                        painterResource(id = R.drawable.ic_inspect_vehicle),
                        contentDescription = "image inspect vehicle",
                        tint = Color(0xFF227EE0)
                    )

                    //Title
                    Text(
                        text = vehicle.vehicle_name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF090D10),
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                //Check indicator
                Text(
                    text = if (vehicle.inspected == 0L) "Required" else "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF090D10),
                    ),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .background(
                            color = Color(0xFFFECACA),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            //Last inspection label
            Text(
                text = "Last Inspection 06 Sep 2023 04:58 p.m.",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF707070),
                )
            )

        }
    }
}