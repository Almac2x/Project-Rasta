package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.components.CustomGemProgressBar
import com.rastatech.projectrasta.ui.components.CustomImageWithHeart
import com.rastatech.projectrasta.ui.components.CustomVoteButton
import com.rastatech.projectrasta.ui.components.VoteType
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
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun WishItemPageScreen(
    wishName: String,
    wisherName: String,
    userRastaGems: Int,
    minRastaGems: Int,
    maxRastaGems: Int,
    upVote: Int,
    downVote: Int,
    voteState: VoteType,
    reason: String,
    donors: List<Int> // TODO change data type later
) {
    val gems = remember { mutableStateOf(minRastaGems) }
    val openDialog = remember { mutableStateOf(false)  }
    val nGems = remember { mutableStateOf(0) }

    val dividerHeight = 5.dp
    val dividerColor = Color.Black
    val space = 15.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back Button
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {  }
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

            // Rasta Gem Logo and Rasta Gem Count
            Row {
                // Rasta Gem Logo
                Image(
                    painter = painterResource(id = R.drawable.rastagems),
                    contentDescription = ""
                )
                Text(text = "$userRastaGems", Modifier.align(Alignment.CenterVertically))
            }
        }

        Spacer(modifier = Modifier.height(space))

        CustomImageWithHeart(painter = painterResource(id = R.drawable.gift))

        Spacer(modifier = Modifier.height(space))

        // Donate Button
        Button(
            onClick = {
                openDialog.value = true
            }
        ) {
            Text(text = "Donate")
        }

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
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            gems.value += nGems.value
                            nGems.value = 0
                        }) {
                        Text("Donate")
                    }
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

        Spacer(modifier = Modifier.height(space))

        // Progress Bar
        CustomGemProgressBar(
            progressColor = Color.Green,
            backgroundColor = Color.LightGray,
            progress = gems.value,
            maxProgress = maxRastaGems,
            height = 25.dp
        )

        Spacer(modifier = Modifier.height(space))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Wish Name and Name of Wisher
            Column {
                Text(text = wishName, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text(text = wisherName, fontSize = 15.sp)
            }
            // UpVote DownVote Button
            CustomVoteButton(upvoteCount = upVote, downVoteCount = downVote, voteType = voteState)
        }

        Spacer(modifier = Modifier.height(space))

        // Divider
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(dividerHeight)
            .background(dividerColor)
        )

        WishItemPageTabScreen(
            reason = "Hello World",
            donators = listOf()
        )
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    WishItemPageScreen(
        wishName = "Wish",
        wisherName = "Hello",
        userRastaGems = 200,
        minRastaGems = 50,
        maxRastaGems = 200,
        upVote = 0,
        downVote = 200,
        voteState = VoteType.NONE,
        reason = "IDK",
        donors = listOf(1, 2, 3)
    )
}