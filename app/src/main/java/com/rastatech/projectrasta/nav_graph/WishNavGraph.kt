package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.add_update_wish.AddUpdateWishScreen
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.UserProfileScreen
import com.rastatech.projectrasta.features.splash.presentation.screens.SplashScreen
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import com.rastatech.projectrasta.ui.components.WishList

const val WISH_GRAPH_ROUTE = "wish_graph_route"
const val WISH_LIST_PAGE = "wish_list"
const val WISH_PAGE_ROUTE = "wish_page"
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishGraph(navController : NavHostController, wishList : List<WishDTO>) {

    NavHost(
        navController = navController,
        startDestination = WISH_LIST_PAGE,
        route = WISH_GRAPH_ROUTE

    ){

        composable(route = "$WISH_LIST_PAGE"){
            WishList(navController = navController, wishEntities = wishList)
        }

        composable(route = "$WISH_PAGE_ROUTE"){



        }

    }

}