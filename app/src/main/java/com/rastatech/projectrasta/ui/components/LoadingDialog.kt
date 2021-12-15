package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingDialog(

     isVisible: State<Boolean>

){

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.

        },

        text = {
           Row(verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.fillMaxWidth()) {

               Surface(modifier = Modifier.padding(end = 20.dp)) {
                   CircularProgressIndicator()
               }

               Text(text = "Loading", fontWeight = FontWeight.Bold, fontSize = 28.sp )
           }
        },
        confirmButton = {

        },

    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

    Surface(modifier = Modifier.fillMaxSize()) {
        LoadingDialog(isVisible = remember { mutableStateOf(true)})
    }

}