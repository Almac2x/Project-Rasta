package com.rastatech.projectrasta.screens

import android.util.Log
import android.view.Display
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeViewModel
import com.rastatech.projectrasta.nav_graph.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.HOME_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.MAIN_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.ui.components.WishList


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    bottomBarNavController: NavController,
    viewModel:HomeViewModel = hiltViewModel(),
){

    WishList(navController = bottomBarNavController, displayType = DisplayType.ReadOnly,
        wishEntities = viewModel.allWishes, updateList = {viewModel.updateList()} )


}
