package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/03/2021
 */

/**
 * Custom Icon Button with Bottom Text
 *
 * @param id id of the image to be displayed
 * @param title title to be displayed
 * @param onClick will be called when the user clicks the button
 */
@Composable
fun CustomIconButton(id: Int, title: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = id), contentDescription = title)
            Text(text = title)
        }
    }
}