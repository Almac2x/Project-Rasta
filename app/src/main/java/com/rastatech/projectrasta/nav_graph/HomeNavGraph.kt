package com.rastatech.projectrasta.nav_graph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.presentation.screens.MainScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

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
        route = "$HOME_GRAPH_ROUTE",

    ){

        composable(route = MAIN_GRAPH_ROUTE,

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

            MainScreen(mainNavController = navController)
        }
    }
}