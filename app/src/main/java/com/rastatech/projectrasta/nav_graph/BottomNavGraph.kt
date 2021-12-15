package com.rastatech.projectrasta.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController : NavHostController,  token : String) {

    val tokenNavArgument = listOf(navArgument(NavigationKey.AccessToken.value){
                                                                    type = NavType.StringType
                                                                    defaultValue = token
    })

    NavHost(navController = navController, startDestination = "${BottomBarScreens.Home.route}"
    ){
        composable (route = "${BottomBarScreens.Home.route}",
        arguments = tokenNavArgument
        ){
            HomeScreen()
        }
        composable(route = "${BottomBarScreens.Profile.route}",
            arguments = tokenNavArgument){

            UserProfileScreen(navController = navController, userType = UserType.Current)
        }
        composable(route = "${BottomBarScreens.MakeWish.route}",
            arguments = tokenNavArgument){

            AddUpdateWishScreen(processType = WishProcess.Add)
        }
        composable(route ="${BottomBarScreens.GemsPage.route}",
            arguments = tokenNavArgument){
            GemPageScreen(navController = navController)
        }

        composable( route ="user_transactions"){

            TransactionScreen()
        }

        composable( route ="wish_update/{${NavigationKey.WishID.value}}",
        arguments = listOf(navArgument(NavigationKey.WishID.value){
            type = NavType.IntType
            
        })){
            AddUpdateWishScreen(processType = WishProcess.Update)
            
        }



    }
}