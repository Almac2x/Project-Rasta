package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.UserProfileScreen
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreens.Home.route
    ){
        composable (route = BottomBarScreens.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreens.Profile.route){
            UserProfileScreen(firstName = "Christian ", lastName = "Salon", userName = "Lloyd")
        }
        composable(route = BottomBarScreens.MakeWish.route){
           //Add here the Make Wish Page
        }
        composable(route = BottomBarScreens.GemsPage.route ){
            GemPageScreen(navController = navController)
        }
    }
}