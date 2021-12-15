package com.rastatech.projectrasta.features.main.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.nav_graph.BottomNavGraph
import com.rastatech.projectrasta.ui.theme.AppColorPalette

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(

    mainNavController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
){
    val bottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            // BottomBar(navController = bottomBarNavController)
            BottomAppBar(
                cutoutShape = CircleShape,
                backgroundColor = MaterialTheme.colors.background
            ) {
                BottomBar(navController = bottomBarNavController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.border(
                    3.dp,
                    MaterialTheme.colors.secondary,
                    shape = CircleShape
                ),
                backgroundColor = MaterialTheme.colors.background,
                onClick = {
                    // do something
                }
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())){
            BottomNavGraph(navController = bottomBarNavController, token = viewModel.userToken)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController){

    val screens = listOf(
        BottomBarScreens.Home, BottomBarScreens.MakeWish, BottomBarScreens.GemsPage,BottomBarScreens.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        screens.forEach{screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination ,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        alwaysShowLabel = false,
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        selectedContentColor = AppColorPalette.secondary,
        unselectedContentColor = Color.Black,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(BottomBarScreens.Home.route)
                launchSingleTop = true
            }
        },
    )
}