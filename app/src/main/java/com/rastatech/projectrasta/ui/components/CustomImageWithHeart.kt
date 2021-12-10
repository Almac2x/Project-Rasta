package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
fun CustomImageWithHeart(painter: Painter, isHeart: Boolean = false) {
    val heart = remember {
        mutableStateOf(isHeart)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    heart.value = !heart.value
                }
            ),
        shape = CardCornerRadius.medium,
        elevation = 5.dp
    ) {
        Image(painter = painter, contentDescription = "")
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
                    heart.value = !heart.value
                }
            ) {
                Icon(
                    tint = if (heart.value) Color.Red else Color.LightGray,
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
        CustomImageWithHeart(
            painter = painterResource(id = R.drawable.gift),
            isHeart = false
        )
    }
}
