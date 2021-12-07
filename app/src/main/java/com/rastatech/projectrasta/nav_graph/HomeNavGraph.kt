package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.features.main.presentation.screens.MainScreen


const val MAIN_GRAPH_ROUTE = "main_screen"

@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {

    navigation(
       startDestination = MAIN_GRAPH_ROUTE,
        route = HOME_GRAPH_ROUTE
    ){

        composable(route = MAIN_GRAPH_ROUTE){
            MainScreen()
        }

        composable (route = BottomBarScreens.Home.route){
            HomeScreen()
        }

    }

}