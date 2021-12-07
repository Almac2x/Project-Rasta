package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreens.Home.route
    ){
        composable (route = BottomBarScreens.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreens.Profile.route){
            HomeScreen()
        }
    }
}