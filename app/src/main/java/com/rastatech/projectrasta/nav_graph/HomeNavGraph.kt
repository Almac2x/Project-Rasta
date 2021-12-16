package com.rastatech.projectrasta.nav_graph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.presentation.screens.MainScreen
import com.rastatech.projectrasta.nav_graph.util.NavigationKey

/**
 * This File might not be in used
 */
const val MAIN_GRAPH_ROUTE = "main_screen"
const val TAG = "HomeNavGraph"


@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
       startDestination = MAIN_GRAPH_ROUTE,
        route = "$HOME_GRAPH_ROUTE/{${NavigationKey.AccessToken.value}}",
        arguments = listOf(navArgument(NavigationKey.AccessToken.value){
            type = NavType.StringType
        })

    ){

        composable(route = MAIN_GRAPH_ROUTE,
        arguments = listOf(navArgument(NavigationKey.AccessToken.value){
            type = NavType.StringType
        })
             ){
            Log.i(TAG, "Access_Token Received: ${it.arguments?.getString(NavigationKey.AccessToken.value)}")

            MainScreen(mainNavController = navController)
        }
    }
}