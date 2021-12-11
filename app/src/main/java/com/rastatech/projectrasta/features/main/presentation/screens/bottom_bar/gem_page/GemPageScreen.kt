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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.domain.util.ShowType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageEvents
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageViewModel
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
    navController: NavController,
    viewModel: GemPageViewModel = hiltViewModel()
) {
    val space = 30.dp
    val boxSize = 200.dp

    val nGems = remember { mutableStateOf(0) }

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
                        GemPageEvents.AddGemDialog( showType = ShowType.Open)
                    }
                )
                CustomIconButton(id = R.drawable.gift, title = "Send Gems",
                    onClick = {
                        GemPageEvents.SendGemDialog(showType = ShowType.Open)
                })
                CustomIconButton(id = R.drawable.gift, title = "Transactions",
                    onClick = {
                    GemPageEvents.TransactionGemDialog(showType = ShowType.Open)
                })
            }
        }
    }

    if (viewModel.showAddGemDialog.value) {
        AlertDialog( // Make this into a seperate Composable
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
                        value = if (viewModel.gemBalance == 0) "" else viewModel.gemBalance.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            GemPageEvents.CheckInput(input = viewModel.gemBalance.toString())
                        }
                    )
                }
            },
            onDismissRequest = {
                GemPageEvents.AddGemDialog(showType = ShowType.Close)
            },
            confirmButton = {
                GemPageEvents.AddGemDialog(showType = ShowType.Close)
            },
            dismissButton = {
                GemPageEvents.AddGemDialog(showType = ShowType.Close)
            }
        )
       // nGems.value = 0 <- Di ko alam kung papaano ito
    }
}