package com.rastatech.projectrasta.features.splash_login_signup.presentation.signup

import androidx.lifecycle.ViewModel
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

    private val userUseCases: UserUseCases

):ViewModel(){



}