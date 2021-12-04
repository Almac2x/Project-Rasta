package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rastatech.projectrasta.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.features.splash.presentation.screens.SplashScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.screens.LoginScreen
import com.rastatech.projectrasta.features.signup.SignUpScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AuthScreens.Login.route,
        route = AUTH_GRAPH_ROUTE
    ){

        composable (route = AuthScreens.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = AuthScreens.SignUp.route){
           SignUpScreen(navController = navController)
        }
    }

}