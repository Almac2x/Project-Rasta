package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case


import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.OrderType
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllUsers(
    private val repository: UserRepository
){

    operator fun invoke(userOrder: UserOrder = UserOrder.LastName(OrderType.Descending)

    ): Flow<List<UserEntity>> {

        return repository.getUsers().map{users ->

            when(userOrder.orderType){
                is OrderType.Ascending ->{
                    when(userOrder) {
                        is UserOrder.UserName -> users.sortedBy { it.user_name.lowercase() }
                        is UserOrder.FirstName -> users.sortedBy { it.first_name.lowercase() }
                        is UserOrder.LastName -> users.sortedBy { it.last_name.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when(userOrder) {
                        is UserOrder.UserName -> users.sortedByDescending { it.user_name.lowercase() }
                        is UserOrder.FirstName -> users.sortedByDescending { it.first_name }
                        is UserOrder.LastName -> users.sortedByDescending { it.last_name }
                    }
                }

            }
        }

    }

}