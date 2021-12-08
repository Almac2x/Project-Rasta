package com.rastatech.projectrasta.features.splash_login_signup.domain.use_case

data class UserUseCases (

    val getAllUsers: GetAllUsers,
    val deleteUser: DeleteUser,
    val getSingleUser: GetSingleUser,
    val addUser: AddUser,

    val addUserApiRequest: AddUserApiRequest,
    val getLoginTokenApiRequest: GetLoginTokenApiRequest
        ){

}