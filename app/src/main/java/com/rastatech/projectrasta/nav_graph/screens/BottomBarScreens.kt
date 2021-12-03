package com.rastatech.projectrasta.nav_graph.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector

){
    object Home: BottomBarScreens(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: BottomBarScreens(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Settings: BottomBarScreens(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
