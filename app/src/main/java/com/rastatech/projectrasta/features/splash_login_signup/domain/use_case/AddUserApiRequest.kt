package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository

class AddUserApiRequest(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: UserRequestDTO){
        repository.createUserApiRequest(user = user)
    }

}