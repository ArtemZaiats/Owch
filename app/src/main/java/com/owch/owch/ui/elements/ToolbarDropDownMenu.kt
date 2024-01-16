package com.owch.owch.ui.elements

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ToolbarDropDownMenu(expanded: Boolean, setExpanded: (Boolean) -> Unit) {

    val context = LocalContext.current
    val (isOpenDialog, setOpenDialog) = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(top = 8.dp, end = 16.dp)
    ) {

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Settings") },
                onClick = { Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show() },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "settings"
                    )
                }
            )

            DropdownMenuItem(
                text = { Text(text = "Delete") },
                onClick = { Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show() },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete"
                    )
                }
            )

            DropdownMenuItem(
                text = { Text(text = "Info") },
//                onClick = { Toast.makeText(context, "Info", Toast.LENGTH_SHORT).show() },
                onClick = { setOpenDialog(true) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "info"
                    )
                }
            )
        }
    }

    CustomDialog(isOpenDialog = isOpenDialog, setIsOpenDialog = setOpenDialog)
}

@Composable
fun CustomDialog(isOpenDialog: Boolean, setIsOpenDialog: (Boolean) -> Unit) {
    if (isOpenDialog) {
        AlertDialog(
            onDismissRequest = { setIsOpenDialog(false) },
            confirmButton = {
                TextButton(onClick = { setIsOpenDialog(false) }) { Text(text = "Confirm") }
            },
            dismissButton = {
                TextButton(onClick = { setIsOpenDialog(false) }) {
                    Text(text = "Dismiss")
                }
            },
            title = { Text(text = "Dialog title", textAlign = TextAlign.Center) },
            text = { Text(text = "Dismiss the dialog when the user clicks outside the dialog or on the back button. If you want to disable that functionality, simply use an empty onCloseRequest.") }
        )
    }
}