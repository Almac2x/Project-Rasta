package com.rastatech.projectrasta

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.theme.components.WishList

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route
    ){
        composable (route = BottomBarScreen.Home.route){
                HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
                HomeScreen()
        }
    }

}