package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

sealed class UserProfileEvents{

    object GetProfile: UserProfileEvents()
    object Logout: UserProfileEvents()
}
