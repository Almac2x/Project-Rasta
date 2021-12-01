package com.rastatech.projectrasta.features.home.domain.usecases

import com.rastatech.projectrasta.features.home.domain.repositories.UserRepository

class GetUsers(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}