package com.rastatech.projectrasta.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.R
import com.skydoves.landscapist.glide.GlideImage

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */


/**
 * Custom Profile Image
 *
 * @param painter to draw
 * @param diameter diameter of the circle
 * @param borderThickness border thickness, default is 8.dp
 */
@Composable
fun CustomProfileImage(
    url: String,
    diameter: Dp,
    borderThickness: Dp = 8.dp
) {
    val imageScale = 0.9f - (borderThickness.value / 100f)

    val linearGradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF405DE6),
            Color(0xFF5851DB),
            Color(0xFF833AB4),
            Color(0xFFC13584),
            Color(0xFFE1306C),
            Color(0xFFFD1D1D),
            Color(0xFFF56040),
            Color(0xFFF77737),
            Color(0xFFFCAF45),
            Color(0xFFFFDC80)
        )
    )

    Box(
        modifier = Modifier
            .size(diameter)
            .clip(CircleShape)
            .border(borderThickness, linearGradientBrush, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // Profile Picture

        GlideImage(imageModel = url,
            modifier = Modifier.fillMaxSize(),
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
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CustomProfileImage(
        url = "",
        diameter = 190.dp,
        borderThickness = 5.dp
    )
}