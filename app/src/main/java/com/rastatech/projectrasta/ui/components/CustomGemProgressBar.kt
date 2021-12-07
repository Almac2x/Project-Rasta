package com.rastatech.projectrasta.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/03/2021
 */

/**
 * Custom Progress Bar
 *
 * @param progress progress value of the progress bar
 * @param maxProgress maximum value of the progress bar
 * @param progressColor progress color, default is Color.Blue
 * @param textColor color of the text to be displayed
 * @param backgroundColor background color of progress bar, default is Color.White
 * @param height height of the progress bar, default is 15 dp
 * @param cornerShape corner radius shape
 */
@Composable
fun CustomGemProgressBar(
    progress: Int = 0,
    maxProgress: Int = 100,
    progressColor: Color = Color.Blue,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    height: Dp = 15.dp,
    cornerShape: CornerBasedShape = CardCornerRadius.small
) {
    val fontHeight = (height.value * 0.70).sp
    val gemIconSize = (height.value * 0.90).dp

    val tempProgress = if (progress > maxProgress) maxProgress else progress

    // get the fraction of the progressbar width to be displayed
    val progressBarWidth = tempProgress.toFloat() / maxProgress.toFloat()

    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .clip(cornerShape)
            .background(backgroundColor)
    ) {
        // Colored Bar
        Box(
            Modifier
                .fillMaxWidth(progressBarWidth)
                .height(height)
                .background(progressColor)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            // Gem Icon
            Image(
                painter = painterResource(id = R.drawable.diamond),
                contentDescription = "",
                Modifier
                    .size(gemIconSize)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(2.dp))

            // Progress Text
            Text(
                text = "$tempProgress/$maxProgress",
                fontSize = fontHeight,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectRastaTheme {
        CustomGemProgressBar(
            progress = 60,
            maxProgress = 100,
            height = 30.dp,
            progressColor = Color.Green
        )
    }
}