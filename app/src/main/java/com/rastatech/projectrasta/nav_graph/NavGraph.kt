package com.rastatech.projectrasta

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rastatech.projectrasta.features.splash.presentation.screens.SplashScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.screens.LoginScreen
import com.rastatech.projectrasta.features.signup.SignUpScreen
import com.rastatech.projectrasta.nav_graph.authNavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rastatech.projectrasta.nav_graph.homeNavGraph

/**
 *
 * This is the Root Navigation Graph of the Application
 *
 */


const val ROOT_GRAPH_ROUTE = "root_graph"
const val AUTH_GRAPH_ROUTE = "root_auth"
const val HOME_GRAPH_ROUTE = "root_home"

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