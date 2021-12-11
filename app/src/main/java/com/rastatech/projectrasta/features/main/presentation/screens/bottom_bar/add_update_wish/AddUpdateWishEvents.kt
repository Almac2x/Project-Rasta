package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish

sealed class AddUpdateWishEvents {


    data class UpdateWish(val wishID : Int): AddUpdateWishEvents() // wishID needed to be pass to event

    object AddWish: AddUpdateWishEvents()

}