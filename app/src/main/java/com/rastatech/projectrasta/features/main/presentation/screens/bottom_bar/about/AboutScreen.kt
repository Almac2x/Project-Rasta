package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.R

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/15/2021
 */

@Composable
fun AboutScreen() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState).padding(10.dp)) {
        // Secret Rasta Text
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

        // Rasta Tech Image

        // About Rasta Tech Text

        // About

        // Meet the Team (drop down items)
        // item: (picture) (name, track) (dropdown button)
        // inside: description
    }
}