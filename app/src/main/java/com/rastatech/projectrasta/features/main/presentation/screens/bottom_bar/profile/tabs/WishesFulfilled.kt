package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.nav_graph.WishGraph
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.components.WishList

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishesFulfilled(wishList : List<WishDTO>) {

    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        WishGraph(navController = navController, wishList = wishList)
    }
}