package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.splash_login_signup.domain.repository.UserRepositoryImpl
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
class GemPageViewModel@Inject constructor(



):ViewModel(){

     fun testGet(){

         viewModelScope.launch(Dispatchers.IO) {

         }
    }
}