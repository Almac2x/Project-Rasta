package com.rastatech.projectrasta.nav_graph.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.domain.util.ListType
import com.rastatech.projectrasta.nav_graph.util.NavigationKey

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,

){

    val nani = {

    }
    object Home: BottomBarScreens(
        route = "home/{${NavigationKey.AccessToken.value}}",
        title = "Home",
        icon = Icons.Default.Home
    ){

    }
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
        route = "make_wish",
        title = "Make A Wish",
        icon = Icons.Default.Star
    )

    object UpdateWish: BottomBarScreens(
        route = "wish_update/{${NavigationKey.WishID.value}}",
        title = "Update Wish",
        icon = Icons.Default.Star
    ){
        fun navigate(wishID: Int):String{
            return "wish_update/$wishID}"
        }
    }

    object WishListPage: BottomBarScreens(
        route = "wish_list/{${NavigationKey.ListType.value}}/{${NavigationKey.DisplayType.value}}",
        title = "",
        icon = Icons.Default.Star
    ){
        fun navigate(displayType: DisplayType, listType: ListType):String{
            return "wish_list/${listType.value}/${displayType.value}"
        }
    }

}
