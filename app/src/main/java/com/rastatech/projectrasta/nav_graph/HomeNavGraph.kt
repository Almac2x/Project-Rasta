package com.rastatech.projectrasta.nav_graph

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.features.main.presentation.screens.MainScreen

/**
 * This File might not be in used
 */
const val MAIN_GRAPH_ROUTE = "main_screen"
const val TAG = "HomeNavGraph"

const val ACCESS_TOKEN_ARGUMENT_KEY = "access_token"
@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {

    navigation(
       startDestination = MAIN_GRAPH_ROUTE,
        route = "$HOME_GRAPH_ROUTE/{$ACCESS_TOKEN_ARGUMENT_KEY}",
        arguments = listOf(navArgument(ACCESS_TOKEN_ARGUMENT_KEY){
            type = NavType.StringType
        })

    ){

        composable(route = MAIN_GRAPH_ROUTE,
        arguments = listOf(navArgument(ACCESS_TOKEN_ARGUMENT_KEY){
            type = NavType.StringType
        })

             ){
            Log.i(TAG, "Access_Token Received: ${it.arguments?.getString(ACCESS_TOKEN_ARGUMENT_KEY)}")

            MainScreen()
        }

    }

}