package com.rastatech.projectrasta.features.splash_login_signup.presentation.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.InvalidUserException
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity

import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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
                viewModelScope.launch {

                    try {
                        userUseCases.addUser( // add user UseCase
                            user =

                            UserEntity(
                                user_name = userName.value.text,
                                first_name =firstName.value.text, // add here FirstName TextField
                                last_name = lastName.value.text, // add Here LastName TextField
                                id = 1, // get the id from API placeholder lang muna
                                email = email.value.text,
                                rasta_gem_balance = 100,
                                avatar = "asdfasdf", // add here the url of the profile

                                refresh_token = "Token Refresh",
                                token = "Token lang",

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