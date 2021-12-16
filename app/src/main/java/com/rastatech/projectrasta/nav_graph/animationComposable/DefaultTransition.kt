package com.rastatech.projectrasta.nav_graph.animationComposable

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi


fun NavGraphBuilder.defaultTransition(route : String, navController: NavHostController,   compose : ()->Unit){

        composable( route = route,

            enterTransition = { initial, target ->

                        slideInHorizontally(
                            initialOffsetX = { 300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))

            }, exitTransition =   { _, target ->
                        slideOutHorizontally(
                            targetOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))

            },
            popEnterTransition = { initial, _ ->
                        slideInHorizontally(
                            initialOffsetX = { -300 },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {_, target ->
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ){
            compose()
        }

}