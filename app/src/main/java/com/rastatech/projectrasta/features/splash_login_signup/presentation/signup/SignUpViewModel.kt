package com.rastatech.projectrasta.features.splash_login_signup.presentation.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.InvalidUserException
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO

import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "SignUpViewModel"

@HiltViewModel
class SignUpViewModel @Inject constructor(

    private val userUseCases: UserUseCases

):ViewModel(){

    //This will be move to SignUpViewModel
    val firstName = mutableStateOf(TextFieldValue())
    val lastName =  mutableStateOf(TextFieldValue())
    val userName = mutableStateOf(TextFieldValue())
    val phoneNumber = mutableStateOf(TextFieldValue())
    val email =  mutableStateOf(TextFieldValue())

    val password =  mutableStateOf(TextFieldValue()) // This field is not stored and will be sent only to the API
    val verifyPassword = mutableStateOf(TextFieldValue())
    ///////

     fun onEvent(event: SignUpEvents){ // This event is supposedly not suspend. I just put suspend to prevent error for add user

        when (event){
            is SignUpEvents.AddUser -> {
                viewModelScope.launch(Dispatchers.IO) {

                    try {
                        userUseCases.addUserApiRequest( // add user UseCase
                            user =

                           UserRequestDTO(
                               email = "alejandro_blando@yahoo.com",
                               first_name = "Alejandro",
                               last_name = "Blando ",
                               password = "password",
                               phone_number = "12345678901",
                               username = "alejandro123",

                           )
                        )
                        Log.i(TAG, "Added ${userName.value.text} to Database")

                    } catch (e: InvalidUserException) {
                        Log.e(TAG,"An Error Occurred, Cannot Add User.")

                    } finally {

                    }


                }

            }
        }
    }

}