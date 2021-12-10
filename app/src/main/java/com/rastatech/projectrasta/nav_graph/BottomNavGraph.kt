package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.gempage.GemPageScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.UserProfileScreen
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController,  token : String) {
    NavHost(navController = navController, startDestination = "${BottomBarScreens.Home.route}/{$ACCESS_TOKEN_ARGUMENT_KEY}"
    ){
        composable (route = "${BottomBarScreens.Home.route}/{$ACCESS_TOKEN_ARGUMENT_KEY}",
        arguments = listOf(navArgument(ACCESS_TOKEN_ARGUMENT_KEY){
            type = NavType.StringType
            defaultValue = token
         }
        )
            ){
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