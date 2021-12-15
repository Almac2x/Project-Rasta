package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.CustomGemProgressBar
import com.rastatech.projectrasta.ui.components.CustomImageWithHeart
import com.rastatech.projectrasta.ui.components.vote_button.CustomVoteButton
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.utils.ValidateInput

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */

/**
 * Wish Item Page Screen
 *
 * @param wishName name of the wish
 * @param wisherName name of wisher
 * @param userRastaGems user number of rasta gems
 * @param minRastaGems wish rasta gems progress
 * @param maxRastaGems max rasta gems needed for the wish
 * @param upVote upvote count
 * @param downVote downvote count
 * @param reason reason for wishing
 * @param donors list of donors
 */
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun WishItemPageScreen(
    navController: NavController,
    viewModel : WishItemPageViewModel = hiltViewModel(),

) {
    val openDialog = remember { mutableStateOf(false)  }
    val nGems = remember { mutableStateOf(0) }

    val dividerHeight = 5.dp
    val dividerColor = MaterialTheme.colors.onPrimary
    val space = 10.dp

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = AppColorPalette.background, elevation = 0.dp) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    IconButton(
                        onClick = {
                            navController.navigateUp()
                            // Return to previous screen
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    Text(
                        text = BottomBarScreens.GemsPage.title,   // Add Title of wish here
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Row(){
                        // Rasta Gem Logo
                        Image(
                            painter = painterResource(id = R.drawable.rastagems),
                            contentDescription = "",
                        )
                        Text(
                            text = "${viewModel.userRastaGems}",
                            fontSize = 20.sp
                        )
                    }

                }



            }
        }
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back Button
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                // App Name
                Image(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.title),
                    contentDescription = "title"
                )


            }
            */
                    //Between Top Bar and Image WITH HEART
            //Spacer(modifier = Modifier.height(space))

            CustomImageWithHeart(isHeart = viewModel.liked, //
                painter = if (viewModel.imageURL.isBlank())
                    painterResource(id = R.drawable.gift)
                else
                    rememberImagePainter(data = viewModel.imageURL)
            )

            Spacer(modifier = Modifier.height(space))


            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                tint = Color.Green,
                                painter = painterResource(id = R.drawable.diamond),
                                contentDescription = null
                            )
                            Text(
                                text = "Donate Gems",
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    text = {
                        Column(
                            modifier = Modifier
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
                    confirmButton = {/*  <- Balikan
                    Button(
                        onClick = {

                            openDialog.value = false
                            gems.value += nGems.value
                            nGems.value = 0
                        }) {
                        Text("Donate")
                    }*/
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults
                                .buttonColors(
                                    backgroundColor = AppColorPalette.error,
                                    contentColor = AppColorPalette.onError
                                ),
                            onClick = {
                                openDialog.value = false
                                nGems.value = 0
                            }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            // Between Image and Progress Bar
            Spacer(modifier = Modifier.height(space))

            // Progress Bar
            CustomGemProgressBar(
                progressColor = Color.Green,
                backgroundColor = Color.LightGray,
                progress = viewModel.rastaGemsDonated,
                maxProgress = viewModel.rastaGemsRequired,
                height = 25.dp
            )

            Spacer(modifier = Modifier.height(space))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Wish Name and Name of Wisher
                Column (modifier = Modifier.width(200.dp)){
                    Text(text = viewModel.wishName, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 25.sp, fontWeight = FontWeight.Bold)

                    TextButton(onClick = {

                        navController.navigate(BottomBarScreens.OthersProfile.navigate(viewModel.userID)){

                        }
                    }) {
                        Text(text = viewModel.wisherFullName, maxLines = 1, overflow = TextOverflow.Ellipsis,fontSize = 15.sp
                        )
                    }


                }

                Column( horizontalAlignment = Alignment.End

                ) {

                    // Donate Button
                    Button(
                        onClick = {
                            openDialog.value = true
                        }
                    ) {
                        Text(text = "Donate")
                    }
                    // UPVOTE DOWNVOTE Button
                    CustomVoteButton(
                        upvoteCount = mutableStateOf(viewModel.numberOfUpVotes),
                        downVoteCount = mutableStateOf(viewModel.numberOfDownVotes),
                        voteType = viewModel.voteStatus,
                        wishID = viewModel.wishID,
                        horizontalArrangement = Arrangement.End// <- Check ---------------------------------------------
                    )
                }

            }

            Spacer(modifier = Modifier.height(space))

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dividerHeight)
                    .background(dividerColor)
            )

            WishItemPageTabScreen(
                reason = viewModel.reason,
                donators = listOf()
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    WishItemPageScreen(

        navController = rememberNavController()
    )
}