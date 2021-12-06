package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case


import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.InvalidUserException
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepository

class AddUser (

    private val repository :UserRepository
        ) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: UserEntity){


        if(user.id > 1){ // checks if the user ID is valid


            throw InvalidUserException("The UserID is invalid.")
        }
        if(user.email.isBlank()){

            throw InvalidUserException("The email of the User cannot be Empty.")
        }
        /**
         * Throw more execeptions here for the UserEntity
         */

        //Uses the InsertUser Function
        repository.insertUser(user)
    }
}