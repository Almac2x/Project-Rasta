package com.rastatech.projectrasta.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.ui.components.wish_list_page.WishList

const val WISH_GRAPH_ROUTE = "wish_graph_route"
const val WISH_LIST_PAGE = "wish_list"
const val WISH_PAGE_ROUTE = "wish_page"
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishGraph(navController : NavHostController, wishList : List<WishDTO>, displayType: DisplayType) {

    NavHost(
        navController = navController,
        startDestination = WISH_LIST_PAGE,
        route = WISH_GRAPH_ROUTE

    ){

        composable(route = "$WISH_LIST_PAGE"){
            WishList(navController = navController, wishEntities = wishList, displayType = displayType)
        }

        composable(route = "$WISH_PAGE_ROUTE"){

        }

    }

}