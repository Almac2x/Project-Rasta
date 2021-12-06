package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository

class GetLoginTokenApiRequest(

    private val repository: UserRepository
) {

    suspend operator fun invoke(username: String, password: String){
        repository.getLoginTokenApiRequest( username = username, password = password)
    }

}