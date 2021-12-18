package com.rastatech.projectrasta.features.main.domain.use_case.main_use_case

import com.rastatech.projectrasta.features.main.data.remote.dto.UserDTO
import com.rastatech.projectrasta.features.main.domain.repository.MainRepository
import retrofit2.Response

class GetOwnProfile(
    private val repository: MainRepository
) {

    suspend operator fun invoke(token : String): Response<UserDTO> {

      return repository.getOwnProfile(token = token)

    }
}