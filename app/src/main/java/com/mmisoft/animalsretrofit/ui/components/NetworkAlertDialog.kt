package com.mmisoft.animalsretrofit.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun NetworkAlertDialog(
    dismissDialog: () -> Unit
) {
    AlertDialog(onDismissRequest = { dismissDialog() },
        text = {
            Text(
                text = "No Internet Connection"
            )
        },
        confirmButton = {
            Button(onClick = { dismissDialog() }) {
                Text(text = "Check Internet Connection")
            }
        })
}
