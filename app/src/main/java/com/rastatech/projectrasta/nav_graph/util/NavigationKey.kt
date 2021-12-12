package com.rastatech.projectrasta.nav_graph.util

sealed class NavigationKey{

    object AccessToken: NavigationKey(){
        const val value = "access_token"
    }
    object WishID: NavigationKey(){
        const val value = "wish_id"
    }

}
