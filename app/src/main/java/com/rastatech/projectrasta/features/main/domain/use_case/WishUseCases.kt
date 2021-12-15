package com.rastatech.projectrasta.features.main.domain.use_case

import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.*

data class WishUseCases(
    val getWish: GetWish,
    val createAWish: CreateAWish,
    val getHomeScreenWishes: GetHomeScreenWishes,
    val likeAWish: LikeAWish,
    val getUserWishList: GetUserWishList,
){
}