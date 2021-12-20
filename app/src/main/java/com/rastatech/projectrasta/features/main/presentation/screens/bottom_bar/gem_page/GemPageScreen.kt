package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.zxing.WriterException
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.features.main.domain.util.ShowType
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.ShimmerAnimation
import com.rastatech.projectrasta.ui.components.CustomIconButton
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.utils.Convert
import com.rastatech.projectrasta.utils.ValidateInput
import com.rastatech.projectrasta.utils.animations.Pulsating
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.shimmer

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
@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GemPageScreen(
    navController: NavController,
    viewModel: GemPageViewModel = hiltViewModel()

) {
    val space = 30.dp
    val boxSize = 200.dp

    val animVisibleState = remember { MutableTransitionState(true) }
        .apply { false }


    //Toast
    if(viewModel.showToast.value){

        // This only solves, Error Exception not 403 Errors
        Toast.makeText(LocalContext.current, "${viewModel.toastMessage.value}", Toast.LENGTH_SHORT).show()

    }

    //Add Gem Dialog Show or Not
    if (viewModel.showAddGemDialog.value) {
        AddGemsDialog(viewModel = viewModel)
    }
    //Send Gem Dialog Show or Not
    else if (viewModel.showSendGemDialog.value) {
        SendGemDialog(viewModel = viewModel)
    }

    //Show QR Code Dialog
    if(viewModel.showQRCodeDialog.value){
        QRDialog(isVisible = viewModel.showQRCodeDialog, userName = viewModel.userName)
    }
    
    

    // UI
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = AppColorPalette.background, elevation = 0.dp) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomStart
                ) {

                    IconButton(
                        onClick = {
                            viewModel.showQRCodeDialog.value = true

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.QrCode,
                            contentDescription = "QR Code"
                        )
                    }

                    Text(modifier = Modifier.fillMaxWidth(),
                        text = BottomBarScreens.GemsPage.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                }

            }
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box( modifier = Modifier
                .fillMaxWidth()
                .height(100.dp), contentAlignment = Alignment.Center) {
                // Number of Gems <- Start
                val animationDuration = 2000

                this@Column.AnimatedVisibility(
                    //modifier = Modifier.align(Alignment.CenterHorizontally),
                    visibleState = animVisibleState,
                    enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 1000))
                ){

                    Box(modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        ShimmerAnimation()
                    }
                }

                if(viewModel.gemBalance > -1){

                    animVisibleState.targetState = false

                    if(!animVisibleState.targetState && !animVisibleState.currentState){

                        this@Column.AnimatedVisibility(
                            //modifier = Modifier.align(Alignment.CenterHorizontally),
                            visible = !animVisibleState.targetState && !animVisibleState.currentState,
                            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),

                        ) {
                            // Number of Gems
                            Text(
                                text = Convert.toCompactNumber(viewModel.gemBalance),
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }



                }
            }
            
            Spacer(modifier = Modifier.height(space))

            // Rasta Gem Logo
            Box(modifier = Modifier.size(boxSize)) {
                Pulsating {
                    Image(
                        painter = painterResource(id = R.drawable.rastagems),
                        contentDescription = "Image",
                        Modifier
                            .size(boxSize)
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
                    id = R.drawable.add_gem,
                    title = "Add Gems",
                    onClick = {
                        viewModel.events(GemPageEvents.AddGemDialog(showType = ShowType.Open))
                    }
                )
                // Send Gems Button
                CustomIconButton(
                    id = R.drawable.send_gem,
                    title = "Send Gems",
                    onClick = {
                        viewModel.events(GemPageEvents.SendGemDialog(showType = ShowType.Open))
                    }
                )
                CustomIconButton(id = R.drawable.transactions, title = "Transactions", onClick = {
                    navController.navigate("user_transactions"){
                    }
                })
            }
        }
    }
}


@Composable
private fun QRDialog(isVisible : MutableState<Boolean>,userName: String){

   var  qrgEncoder : QRGEncoder
   var bitmap : Bitmap
    
    Dialog(
        onDismissRequest = { isVisible.value = false },

        content = {

            Surface() {

                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                        //setting this dimensions inside our qr code encoder to generate our qr code.
                        qrgEncoder = QRGEncoder("$userName", null, QRGContents.Type.TEXT, 550)

                        //getting our qrcode in the form of bitmap.
                        bitmap = qrgEncoder.encodeAsBitmap()
                        // the bitmap is set inside our image view using .setimagebitmap method.

                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = ""
                        )

                        Text(modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Your QR")

                    }

                }
            }


        },

    )

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

                // Issue when developer used keyboard on emulator please handle kung may time
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = if (nGems.value == 0) "" else nGems.value.toString(),
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
                    viewModel.events(GemPageEvents.AddGems(showType = ShowType.Close, amount = nGems.value))
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
                    viewModel.events(GemPageEvents.SendGems(showType = ShowType.Close, amount = nGems.value, userName = username.value.text))
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
    nGems.value = 0
}