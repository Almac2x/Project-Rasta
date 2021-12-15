package com.rastatech.projectrasta.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.vote_button.CustomVoteButton
import com.rastatech.projectrasta.ui.components.wish_list_page.WishPageEvents
import com.rastatech.projectrasta.ui.components.wish_list_page.WishViewModel
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/09/2021
 */

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun CustomWishTile(
    wishEntity: WishDTO? = null,
    navController: NavController,
    viewModel: WishViewModel,
    displayType: DisplayType,
    updateList: (() -> Unit)? = null,


    ) {
    val tileHeight = 300.dp
    val tileElevation = 5.dp
    val heartButtonSize = 35.dp

    val openDialog = remember { mutableStateOf(false)  }
    val heart = remember { mutableStateOf(wishEntity?.liked ?: false) }

    Card(
        modifier = Modifier
            .height(tileHeight)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .combinedClickable(
                onClick = {

                    navController.navigate(BottomBarScreens.WishItem.navigate(wishID = wishEntity?.wish_id!!)) {

                    }
                    // go to Wish Item Page
                },
                onLongClick = {

                    if (displayType == DisplayType.Editable) {
                        // update or delete alert dialog
                        openDialog.value = true
                    }

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

                //Add Here Coil Image
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    painter = painterResource( // Ilagay Dito Coil
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
                                text = wishEntity?.wish_name.toString(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = wishEntity?.wish_owner_username.toString(), // dapat name ng wisher
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Box(// Heart
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(
                            modifier = Modifier.size(heartButtonSize),
                            onClick = {
                                heart.value = !heart.value
                                viewModel.onEvent(WishPageEvents.LikeAWish(wishID = wishEntity?.wish_id!!))
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
                    progress = wishEntity?.rastagems_donated?:0,
                    maxProgress = wishEntity?.rastagems_required?:0,
                    progressColor = Color.Green,
                    backgroundColor = Color.LightGray,
                    height = 20.dp
                )

                CustomVoteButton(upvoteCount = remember{ mutableStateOf(wishEntity?.upvotes?:0)},

                    downVoteCount = remember { mutableStateOf( wishEntity?.downvotes?:0)},
                    voteType = VoteType.toConvert().convert(wishEntity?.vote_status.toString())?:VoteType.NONE,
                     wishID = wishEntity?.wish_id?:0 )
            }
        }
    }//////// End of Card

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
                        navController.navigate(BottomBarScreens.UpdateWish.navigate(wishEntity?.wish_id?:0)){

                        } // required arguments
                        openDialog.value = false
                    }
                ) {
                    Text(text = "Update")
                }
            },
            dismissButton = {
                Button(    //  <- Delete Button
                    colors = ButtonDefaults
                        .buttonColors(
                            backgroundColor = AppColorPalette.error,
                            contentColor = AppColorPalette.onError
                        ),
                    onClick = {


                            viewModel.onEvent(WishPageEvents.DeleteWish(wishID = wishEntity?.wish_id!!))

                            updateList?.invoke()

                        openDialog.value = false
                    }
                ) {
                    Text(text = "Delete")
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Preview(showBackground = false)
@Composable
private fun Preview() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        CustomWishTile(
            wishEntity = WishDTO(
            wish_name = "Nani", description = "nani", image_url = "url", rastagems_required = 2,
            rastagems_donated = 1, wish_id = 1, liked = false, upvotes = 1, downvotes = 1,
            wish_owner_full_name = "rasta", wish_owner_username = "12", vote_status = VoteType.DOWNVOTE.value,
            wish_owner_id = 1),
            navController = rememberNavController(), viewModel = viewModel(), displayType = DisplayType.ReadOnly
        )
    }
}