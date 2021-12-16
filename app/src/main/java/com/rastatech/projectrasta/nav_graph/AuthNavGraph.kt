package com.rastatech.projectrasta.nav_graph

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.features.signup.SignUpScreen
import com.rastatech.projectrasta.features.splash_login_signup.presentation.login.LoginScreen

import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AuthScreens.Login.route,
        route = AUTH_GRAPH_ROUTE,
    ){

        composable (route = AuthScreens.Login.route,
            enterTransition = { initial, target ->
                when (initial.destination.route){
                    AuthScreens.SignUp.route->
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null

                }
            }, exitTransition =   { _, target ->
                when (target.destination.route) {
                    AuthScreens.SignUp.route ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    AuthScreens.SignUp.route ->
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null
                }
            }

        ){
             LoginScreen(navController = navController)
        }




        composable(route = AuthScreens.SignUp.route){
           SignUpScreen(navController = navController)
        }
    }
}