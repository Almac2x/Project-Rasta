package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.domain.util.ShowType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageEvents
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageViewModel
import com.rastatech.projectrasta.ui.components.CustomIconButton
import com.rastatech.projectrasta.utils.Convert
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
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GemPageScreen(
    navController: NavController,
    viewModel: GemPageViewModel = hiltViewModel()

) {
    val space = 30.dp
    val boxSize = 200.dp

    if (viewModel.showAddGemDialog.value) {
        AddGemsDialog(viewModel = viewModel)
    }
    else if (viewModel.showSendGemDialog.value) {
        SendGemDialog(viewModel = viewModel)
    }

    // UI
    Scaffold {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Number of Gems
            Text(
                text = Convert.toCompactNumber(1000),
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )

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
                // Add Gems Button
                CustomIconButton(
                    id = R.drawable.gift,
                    title = "Add Gems",
                    onClick = {
                        viewModel.events(GemPageEvents.AddGemDialog(showType = ShowType.Open))
                    }
                )
                // Send Gems Button
                CustomIconButton(
                    id = R.drawable.gift,
                    title = "Send Gems",
                    onClick = {
                        viewModel.events(GemPageEvents.SendGemDialog(showType = ShowType.Open))
                    }
                )
                CustomIconButton(id = R.drawable.gift, title = "Transactions", onClick = {})
            }
        }
    }
}

@Composable
private fun AddGemsDialog(viewModel: GemPageViewModel) {
    val nGems = remember { mutableStateOf(0) }

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
            viewModel.events(GemPageEvents.AddGemDialog(showType = ShowType.Close))
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.events(GemPageEvents.AddGemDialog(showType = ShowType.Close))
                }
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.events(GemPageEvents.AddGemDialog(showType = ShowType.Close))
                }
            ) {
                Text(text = "Cancel")
            }
        }
    )

    nGems.value = 0
}

@Composable
private fun SendGemDialog(viewModel: GemPageViewModel) {
    val nGems = remember { mutableStateOf(0) }
    val username = remember { mutableStateOf(TextFieldValue()) }

    AlertDialog(
        title = {
            Text(text = "Send Gems")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Enter amount:",
                    color = Color.Black,
                    fontSize = 16.sp
                )

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

                Text(
                    text = "Username",
                    color = Color.Black,
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black),
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    }
                )
            }
        },
        onDismissRequest = {
            viewModel.events(GemPageEvents.SendGemDialog(showType = ShowType.Close))
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.events(GemPageEvents.SendGemDialog(showType = ShowType.Close))
                }
            ) {
                Text(text = "Send")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.events(GemPageEvents.SendGemDialog(showType = ShowType.Close))
                }
            ) {
                Text(text = "Cancel")
            }
        }
    )
}