package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

@Composable
fun CustomTextWithCount(
    title: String,
    count: Int = 0
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = title, fontSize = 16.sp)
        Text(text = "$count", fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CustomTextWithCount(title = "Followers", count = 25)
}