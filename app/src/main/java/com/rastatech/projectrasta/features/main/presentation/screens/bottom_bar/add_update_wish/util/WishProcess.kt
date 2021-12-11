package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util

sealed class WishProcess(
    val toString: String

) {
    object Add: WishProcess(
        toString = "ADD"
    )


    object Update: WishProcess(
            toString = "UPDATE")


}