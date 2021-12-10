package com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rastatech.projectrasta.features.main.domain.entities.WishEntity
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
fun DonatorsTabScreen(list: List<WishEntity>) {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreen()
    }
}