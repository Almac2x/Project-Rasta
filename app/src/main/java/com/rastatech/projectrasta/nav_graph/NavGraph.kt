package com.rastatech.projectrasta.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.rastatech.projectrasta.features.splash.presentation.screens.SplashScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.rastatech.projectrasta.features.splash_login_signup.presentation.login.LoginScreen
import com.rastatech.projectrasta.nav_graph.animationComposable.defaultTransition

/**
 *
 * This is the Root Navigation Graph of the Application
 *
 */


const val ROOT_GRAPH_ROUTE = "root_graph"
const val AUTH_GRAPH_ROUTE = "root_auth"
const val HOME_GRAPH_ROUTE = "root_home"

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NavGraph(navController : NavHostController) {


    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreens.Splash.route,
        route = ROOT_GRAPH_ROUTE

    ){

        composable(route = AuthScreens.Splash.route,
            enterTransition = { initial, target ->
                when (initial.destination.route){
                    AuthScreens.Login.route+"anim"->
                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null

                }
            }, exitTransition =   { _, target ->
                when (target.destination.route) {
                    AuthScreens.Login.route+"anim" ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    else -> null
                }
            },
            popEnterTransition = { initial, _ ->
                when (initial.destination.route) {
                    AuthScreens.Login.route+"anim" ->
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    else -> null
                }
            }

            ){
            SplashScreen(navController = navController)
        }

        defaultTransition(navController = navController,
            route = AuthScreens.Login.route+"anim",
            compose = {
                LoginScreen(navController = navController)
            }

        )


        authNavGraph(navController = navController)

        homeNavGraph(navController = navController)

    }

}