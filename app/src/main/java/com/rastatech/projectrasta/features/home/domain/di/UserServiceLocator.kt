package com.rastatech.projectrasta.features.home.domain.di

import com.rastatech.projectrasta.features.home.domain.repositories.UserRepository
import com.rastatech.projectrasta.features.home.domain.usecases.GetUsers

class UserServiceLocator(
    repository: UserRepository
) {

    /**
     * Use Case for retrieving list of users
     * */
    val getUsers = GetUsers(repository)

    /**
     * Use Case for retrieving user's wishes
     * */

}