package com.rastatech.projectrasta.ui.components.wish_list_page

import com.rastatech.projectrasta.features.main.domain.util.VoteType

sealed interface WishPageEvents{

  data class DeleteWish (val wishID: Int): WishPageEvents
  data class Vote(val wishID : Int, val voteType : VoteType): WishPageEvents
  data class LikeAWish(val wishID:Int):WishPageEvents
}