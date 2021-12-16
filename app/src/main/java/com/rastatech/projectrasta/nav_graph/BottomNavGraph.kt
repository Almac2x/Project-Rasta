package com.rastatech.projectrasta.nav_graph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.AddUpdateWishScreen
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.about.AboutScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions.TransactionScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.UserProfileScreen
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.WishItemPageScreen


private const val  BOTTOM_TAG = "BottomGraph"
@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(bottomBarNavController : NavHostController, token : String, mainNavController: NavController) {


    val tokenNavArgument = listOf(navArgument(NavigationKey.AccessToken.value){
                                                                    type = NavType.StringType
                                                                    defaultValue = token
    })

    NavHost(navController = bottomBarNavController, startDestination = "${BottomBarScreens.Home.route}"
    ){
        composable (route = "${BottomBarScreens.Home.route}",  //Home Tab
        arguments = tokenNavArgument
        ){
            HomeScreen( bottomBarNavController = bottomBarNavController)
        }
        composable(route = "${BottomBarScreens.Profile.route}", //Profile Tab
            arguments = tokenNavArgument){

            UserProfileScreen(bottomBarNavController = bottomBarNavController,
                userType = UserType.Current, mainNavController = mainNavController)
        }

        composable(route = BottomBarScreens.OthersProfile.route,
            arguments = listOf(navArgument(NavigationKey.UserID.value){
            type = NavType.IntType


        })){

            UserProfileScreen(bottomBarNavController = bottomBarNavController,
                userType = UserType.Other)

        }



        composable(route = "${BottomBarScreens.MakeWish.route}", // Add Wish Route
          ){

            AddUpdateWishScreen(processType = WishProcess.Add, navController = bottomBarNavController )
        }
        composable(route ="${BottomBarScreens.GemsPage.route}", // Gem Tab
            arguments = tokenNavArgument){
            GemPageScreen(navController = bottomBarNavController)
        }

        composable( route ="${BottomBarScreens.About.route}"){ // About Tab
            // Call About Screen
            AboutScreen()
        }

        composable( route ="user_transactions"){ //Transaction Screen Route
            TransactionScreen(navController = bottomBarNavController)
        }


        composable( route = BottomBarScreens.UpdateWish.route, // Update Wish Route

        arguments = listOf(navArgument(NavigationKey.WishID.value){
            type = NavType.IntType
            defaultValue = -1

        })){

            val id = it.arguments?.getInt(NavigationKey.WishID.value)

            Log.i(BOTTOM_TAG, "$id")

            AddUpdateWishScreen(processType = WishProcess.Update, navController = bottomBarNavController)
        }

        composable(route = BottomBarScreens.WishItem.route,
                arguments = listOf(navArgument(NavigationKey.WishID.value){
                    type = NavType.IntType
                    defaultValue = -1

                })
            ){

            WishItemPageScreen(navController = bottomBarNavController)

        }

        composable(route = "${BottomBarScreens.WishListPage.route}",
            arguments = listOf(navArgument(NavigationKey.ListType.value){
                type = NavType.StringType
            },
                navArgument(NavigationKey.DisplayType.value){
                    type = NavType.StringType
                }
            )
        ){

        }



    }
}