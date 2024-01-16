package com.owch.owch.ui.screens

import android.content.Context
import android.util.Log
import android.widget.RadioGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.owch.owch.R
import com.owch.owch.data.InspectForm
import com.owch.owch.navigation.Screen
import com.owch.owch.ui.elements.ContainerWithToolbar
import com.owch.owch.utils.PreferencesManager
import com.owch.owch.viewModel.FormViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InspectFormListScreen(navHostController: NavHostController, vehicleId: String) {

    val formViewModel: FormViewModel = viewModel()

    val forms by formViewModel.forms.collectAsState()
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val token = "Bearer ${preferencesManager.getToken("token", "")}"

    val (selectedItem, onItemSelected) = remember { mutableLongStateOf(0L) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Log.d("InspectFormListScreen", "vehicleId: $vehicleId")

    LaunchedEffect(key1 = true) {
        formViewModel.fetchForms(token, vehicleId)
    }

    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxHeight()) {
        ContainerWithToolbar(
            title = "Inspect asset",
            onBackIconCLick = { navHostController.popBackStack() }
        ) { }

        // Display the list of forms
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (forms.isNotEmpty()) {
                items(forms) { form ->
                    FormItem(
                        form = form,
                        selectedOption = selectedItem,
                        onOptionSelected = onItemSelected
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                item {
                    Text("No forms available")
                }
            }

        }

        Button(
            enabled = selectedItem > 0L,
            onClick = { navHostController.navigate(Screen.FormDetailsScreen.setFormId(selectedItem)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(text = "Next")
//            Icon(
//                imageVector = if (showBottomSheet) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
//                contentDescription = null
//            )
        }

        if (showBottomSheet) {
            ModalBottomSheetLayout(
                sheetContent = { Text(text = "Sheet text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            ) {

            }
        }
    }
}

@Composable
fun FormItem(
    form: InspectForm, selectedOption: Long,
    onOptionSelected: (Long) -> Unit
) {
    Card(
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (form.id == selectedOption),
                    onClick = { onOptionSelected(form.id) }
                )
                .padding(vertical = 8.dp)
        ) {
            RadioButton(
                selected = (form.id == selectedOption),
                onClick = null,
                modifier = Modifier.padding(10.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF38ACE7),
                )
            )

            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Icon(
                            painterResource(id = R.drawable.ic_inspect_nav_bar),
                            contentDescription = "image inspect vehicle",
                            tint = Color.Black
                        )

                        //Title
                        Text(
                            text = form.title,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF090D10),
                            ),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${form.cardCount} points to check",
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
}

