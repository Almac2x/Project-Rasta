package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/09/2021
 */

@ExperimentalFoundationApi
@Composable
fun CustomWishTile(
    wishEntity: WishEntity? = null,
    isHeart: Boolean = false
) {
    val tileHeight = 300.dp
    val tileElevation = 5.dp
    val heartButtonSize = 35.dp

    val openDialog = remember { mutableStateOf(false)  }
    val heart = remember { mutableStateOf(isHeart) }

    Card(
        modifier = Modifier
            .height(tileHeight)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .combinedClickable(
                onClick = {
                    // go to Wish Item Page
                },
                onLongClick = {
                    // update or delete alert dialog
                    openDialog.value = true
                },
            ),
        shape = CardCornerRadius.small,
        elevation = tileElevation
    ) {
        Box(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    painter = painterResource(
                        id = R.drawable.gift
                    ),
                    contentDescription = "",
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.fillMaxWidth(0.75f)) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Wish Name Hello World",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Wisher",
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(
                            modifier = Modifier.size(heartButtonSize),
                            onClick = {
                                heart.value = !heart.value
                            }
                        ) {
                            Icon(
                                tint = if (heart.value) Color.Red else Color.LightGray,
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.heart_grey),
                                contentDescription = "heart"
                            )
                        }
                    }
                }
                
                CustomGemProgressBar(
                    progress = 30,
                    maxProgress = 200,
                    progressColor = Color.Green,
                    backgroundColor = Color.LightGray,
                    height = 20.dp
                )

                CustomVoteButton(upvoteCount = 0, downVoteCount = 200, voteType = VoteType.NONE)
            }
        }
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = "Update or Delete?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Wish Name: Wish Name",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "What would you like to do with this wish?",
                        color = Color.Black
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(text = "Update")
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
                    }
                ) {
                    Text(text = "Delete")
                }
            }
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = false)
@Composable
private fun Preview() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        CustomWishTile()
    }
}