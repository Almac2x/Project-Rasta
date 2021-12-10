package com.rastatech.projectrasta.features.gempage

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.components.CustomIconButton
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme
import com.rastatech.projectrasta.utils.ValidateInput
import com.rastatech.projectrasta.utils.animations.Pulsating

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/02/2021
 */

/**
 * GemPage Screen
 *
 */
@Composable
fun GemPageScreen(
    navController: NavController
) {
    val space = 30.dp
    val boxSize = 200.dp

    val nGems = remember { mutableStateOf(0) }

    val addGemOpenDialog = remember { mutableStateOf(false) }
    val sendGemOpenDialog = remember { mutableStateOf(false) }
    val transactionOpenDialog = remember { mutableStateOf(false) }

    Scaffold {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Number of Gems
            Text(text = "1,000", fontSize = 60.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(space))

            // Rasta Gem Logo
            Box(modifier = Modifier.size(boxSize)) {
                Pulsating {
                    Image(
                        painter = painterResource(id = R.drawable.rastagems),
                        contentDescription = "Image",
                        Modifier.size(boxSize)
                    )
                }
            }

            Spacer(modifier = Modifier.height(space))

            // Available Gems Text
            Text(text = "Available Gems", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(space))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomIconButton(
                    id = R.drawable.gift,
                    title = "Add Gems",
                    onClick = {
                        addGemOpenDialog.value = true
                    }
                )
                CustomIconButton(id = R.drawable.gift, title = "Send Gems", onClick = {})
                CustomIconButton(id = R.drawable.gift, title = "Transactions", onClick = {})
            }
        }
    }

    if (addGemOpenDialog.value) {
        AlertDialog(
            title = {
                Text(text = "Add Gems")
            },
            text = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Text(
                        text = "Enter amount:",
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, Color.Black),
                        value = if (nGems.value == 0) "" else nGems.value.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            nGems.value = if (ValidateInput.isNumber(it)) it.toInt()
                            else nGems.value
                        }
                    )
                }
            },
            onDismissRequest = {
                addGemOpenDialog.value = false
            },
            confirmButton = {
                addGemOpenDialog.value = false
            },
            dismissButton = {
                addGemOpenDialog.value = false
            }
        )
        nGems.value = 0
    }
}