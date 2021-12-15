package com.rastatech.projectrasta.nav_graph.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
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
        icon = Icons.Outlined.Home
    )
    object Profile: BottomBarScreens(
        route = "profile/{${NavigationKey.AccessToken.value}}",
        title = "Profile",
        icon = Icons.Outlined.Person
    )
    object Settings: BottomBarScreens(
        route = "settings",
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
    object GemsPage: BottomBarScreens(
        route = "mygems/{${NavigationKey.AccessToken.value}}",
        title = "My Gems",
        icon = Icons.Outlined.Money
    )
    object MakeWish: BottomBarScreens(
        route = "make_wish/{${NavigationKey.AccessToken.value}}",
        title = "Make A Wish",
        icon = Icons.Outlined.Star
    )


}
