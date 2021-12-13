package com.rastatech.projectrasta.nav_graph.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.rastatech.projectrasta.nav_graph.util.NavigationKey

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector

){
    object Home: BottomBarScreens(
        route = "home/{${NavigationKey.AccessToken.value}}",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: BottomBarScreens(
        route = "profile/{${NavigationKey.AccessToken.value}}",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Settings: BottomBarScreens(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    object GemsPage: BottomBarScreens(
        route = "mygems/{${NavigationKey.AccessToken.value}}",
        title = "My Gems",
        icon = Icons.Default.Money
    )
    object MakeWish: BottomBarScreens(
        route = "make_wish/{${NavigationKey.AccessToken.value}}",
        title = "Make A Wish",
        icon = Icons.Default.Star
    )


}
