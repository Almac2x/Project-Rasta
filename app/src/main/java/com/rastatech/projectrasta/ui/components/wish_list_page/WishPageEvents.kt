package com.rastatech.projectrasta.ui.components.wish_list_page

sealed interface WishPageEvents{

  data class DeleteWish (val wishID: Int): WishPageEvents
}