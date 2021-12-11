package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish

import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess

sealed class AddUpdateWishEvents {
    data class InitProcess(val type: WishProcess): AddUpdateWishEvents() // initializes what type of process
    data class UpdateWish(val wishID : Int): AddUpdateWishEvents() // wishID needed to be pass to event
    object AddWish: AddUpdateWishEvents()
}