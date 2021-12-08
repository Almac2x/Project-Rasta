package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.TokenDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository
import retrofit2.Response

class GetLoginTokenApiRequest(

    private val repository: UserRepository
) {

    suspend operator fun invoke(username: String, password: String): Response<TokenDTO> {
        return repository.getLoginTokenApiRequest( username = username, password = password)
    }

}