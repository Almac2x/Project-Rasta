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

/**
 * TODO for this Kotlin File
 *  - Please Add logic to password
 *
 * @property userUseCases
 */

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
                         val reqest = userUseCases.addUserApiRequest( // add user UseCase
                            user = UserRequestDTO(
                               email = email.value.text,
                               first_name = firstName.value.text ,
                               last_name = lastName.value.text,
                               password = password.value.text,
                               phone_number = phoneNumber.value.text,
                               username = userName.value.text,
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

    fun checkPassword() : Boolean{

        return true

    }

    fun checkEmail(): Boolean{
        return true
    }

}