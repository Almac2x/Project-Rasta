package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.features.splash.presentation.screens.SplashScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 *
 * This is the Root Navigation Graph of the Application
 *
 */


const val ROOT_GRAPH_ROUTE = "root_graph"
const val AUTH_GRAPH_ROUTE = "root_auth"
const val HOME_GRAPH_ROUTE = "root_home"

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NavGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthScreens.Splash.route,
        route = ROOT_GRAPH_ROUTE

    ){

        composable(route = AuthScreens.Splash.route){
            SplashScreen(navController = navController)
        }

        authNavGraph(navController = navController)

        homeNavGraph(navController = navController)

    }

}