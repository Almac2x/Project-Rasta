package com.rastatech.projectrasta.features.userprofile.presentation.screens.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rastatech.projectrasta.screens.HomeScreen

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishListScreen(list: List<String>) {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen()
    }
}