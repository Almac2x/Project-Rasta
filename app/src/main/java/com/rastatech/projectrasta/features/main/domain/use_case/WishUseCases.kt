package com.rastatech.projectrasta.features.main.domain.use_case

import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.CreateAWish
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.GetHomeScreenWishes
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.GetWish
import com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case.LikeAWish

data class WishUseCases(
    val getWish: GetWish,
    val createAWish: CreateAWish,
    val getHomeScreenWishes: GetHomeScreenWishes,
    val likeAWish: LikeAWish
){
}