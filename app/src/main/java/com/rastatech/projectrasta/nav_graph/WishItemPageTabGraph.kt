package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.ReasonTabScreen
import com.rastatech.projectrasta.nav_graph.screens.WishItemPageTabScreens

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishItemPageTabGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = WishItemPageTabScreens.Reason.route
    ){
        composable (route = WishItemPageTabScreens.Reason.route){
            // Reason Screen
        }
        composable(route = WishItemPageTabScreens.Donors.route){
            // Donors Screen
        }
    }
}