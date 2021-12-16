package com.rastatech.projectrasta.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.skydoves.landscapist.glide.GlideImage

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/03/2021
 */

/**
 * Custom Wish Image with Heart
 *
 * @param painter image to be displayed
 * @param isHeart sets heart value, default is false
 */

@ExperimentalFoundationApi
@Composable
fun CustomImageWithHeart( isHeart: Boolean = false, imageURL :String) {
    val isHeart = remember {
        mutableStateOf(isHeart)
    }

    Log.i("this", imageURL)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    isHeart.value = !isHeart.value
                }
            ),
        shape = CardCornerRadius.medium,
        elevation = 5.dp
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            // Icon Button with Heart Icon
            IconButton(
                modifier = Modifier
                    .fillMaxWidth(0.20f)
                    .fillMaxHeight(0.35f),
                onClick = {
                    isHeart.value = !isHeart.value
                }
            ) {

                GlideImage(imageModel = imageURL,
                    modifier =  Modifier.fillMaxSize(fraction = 0.7f),
                    loading = {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            CircularProgressIndicator()
                        }
                    },
                    failure = {
                        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                            CircularProgressIndicator()
                        }
                    }

                )


                Icon(
                    tint = if (isHeart.value) Color.Red else Color.LightGray,
                    modifier = Modifier.fillMaxSize(fraction = 0.7f),
                    painter = painterResource(R.drawable.heart_grey),
                    contentDescription = "heart"
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun Preview() {
    Scaffold(modifier = Modifier.fillMaxSize()) {

    }
}
