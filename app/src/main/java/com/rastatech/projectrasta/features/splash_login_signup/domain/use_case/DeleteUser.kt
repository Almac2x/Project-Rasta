package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case

import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository

class DeleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user:UserEntity){
        repository.deleteUser(user)
    }


}