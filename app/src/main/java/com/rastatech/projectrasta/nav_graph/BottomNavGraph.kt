package com.rastatech.projectrasta.nav_graph

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.add_update_wish.AddUpdateWishScreen
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.GemPageScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions.TransactionScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.UserProfileScreen
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeScreen


private const val  BOTTOM_TAG = "BottomGraph"
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
        composable (route = "${BottomBarScreens.Home.route}",
        arguments = tokenNavArgument
        ){
            HomeScreen( bottomBarNavController = bottomBarNavController)
        }
        composable(route = "${BottomBarScreens.Profile.route}",
            arguments = tokenNavArgument){

            UserProfileScreen(bottomBarNavController = bottomBarNavController, userType = UserType.Current, mainNavController = mainNavController)
        }
        composable(route = "${BottomBarScreens.MakeWish.route}",
          ){

            AddUpdateWishScreen(processType = WishProcess.Add, navController = bottomBarNavController )
        }
        composable(route ="${BottomBarScreens.GemsPage.route}",
            arguments = tokenNavArgument){
            GemPageScreen(navController = bottomBarNavController)
        }

        composable( route ="${BottomBarScreens.About.route}"){
            // Call About Screen
        }

        composable( route ="user_transactions"){
            TransactionScreen()
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

        composable( route = BottomBarScreens.UpdateWish.route,

        arguments = listOf(navArgument(NavigationKey.WishID.value){
            type = NavType.IntType
            defaultValue = -1

        })){

            val id = it.arguments?.getInt(NavigationKey.WishID.value)

            Log.i(BOTTOM_TAG, "$id")

            AddUpdateWishScreen(processType = WishProcess.Update, navController = bottomBarNavController)
        }



    }
}