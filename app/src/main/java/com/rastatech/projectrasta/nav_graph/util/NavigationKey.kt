package com.rastatech.projectrasta.nav_graph.util

sealed class NavigationKey{

    object AccessToken: NavigationKey(){
        const val value = "access_token"
    }
    object WishID: NavigationKey(){
        const val value = "wish_id"
    }

    object ListType: NavigationKey(){
        const val value = "list_type"
    }

    object DisplayType: NavigationKey(){
        const val value = "display_type"
    }



}
