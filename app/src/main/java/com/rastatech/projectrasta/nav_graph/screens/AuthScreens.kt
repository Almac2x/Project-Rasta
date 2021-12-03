package com.rastatech.projectrasta.nav_graph.screens



sealed class AuthScreens(
    val route: String
) {
    object Splash: AuthScreens(route = "splash_screen")

    object Login: AuthScreens(route = "login_screen")
    object SignUp: AuthScreens(route = "sign_up_screen")
}