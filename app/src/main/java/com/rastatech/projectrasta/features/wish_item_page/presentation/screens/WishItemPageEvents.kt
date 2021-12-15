package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

sealed class WishItemPageEvents{


    class Donate (val amount : Int) : WishItemPageEvents()


}
