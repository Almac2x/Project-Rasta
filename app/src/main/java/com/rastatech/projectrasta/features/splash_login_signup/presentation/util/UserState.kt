package com.rastatech.projectrasta.features.splash_login_signup.presentation.util

import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.OrderType
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder


/**
 * This class is used for ordering users in login
 *
 */
data class UserState(
    val users: List<UserEntity> = emptyList(),
    val userOrder: UserOrder = UserOrder.LastName(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)


