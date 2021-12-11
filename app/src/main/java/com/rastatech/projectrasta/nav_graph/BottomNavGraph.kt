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
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import com.rastatech.projectrasta.screens.HomeScreen

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController,  token : String) {

    val tokenNavArgument = listOf(navArgument(NavigationKey.AccessToken.value){
                                                                    type = NavType.StringType
                                                                    defaultValue = token
    })

    NavHost(navController = navController, startDestination = "${BottomBarScreens.Home.route}"
    ){
        composable (route = "${BottomBarScreens.Home.route}",
        arguments = tokenNavArgument
        ){
            HomeScreen(navController = navController)
        }
        composable(route = "${BottomBarScreens.Profile.route}",
            arguments = tokenNavArgument){

            UserProfileScreen(firstName = "Christian ", lastName = "Salon", userName = "Lloyd")
        }
        composable(route = "${BottomBarScreens.MakeWish.route}",
            arguments = tokenNavArgument){

           //Add here the Make Wish Page
        }
        composable(route ="${BottomBarScreens.GemsPage.route}",
            arguments = tokenNavArgument){
            GemPageScreen(navController = navController)
        }
    }
}