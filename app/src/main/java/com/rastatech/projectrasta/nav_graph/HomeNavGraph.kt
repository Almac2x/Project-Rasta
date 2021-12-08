package com.rastatech.projectrasta.nav_graph

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.features.main.presentation.screens.MainScreen


const val MAIN_GRAPH_ROUTE = "main_screen"
const val TAG = "HomeNavGraph"
@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {

    navigation(
       startDestination = MAIN_GRAPH_ROUTE,
        route = "$HOME_GRAPH_ROUTE/{access_token}"
    ){

        composable(route = MAIN_GRAPH_ROUTE,
        arguments = listOf(navArgument("access_token"){
            type = NavType.StringType
        })

             ){
            Log.i(TAG, "")
            MainScreen()
        }

    }

}