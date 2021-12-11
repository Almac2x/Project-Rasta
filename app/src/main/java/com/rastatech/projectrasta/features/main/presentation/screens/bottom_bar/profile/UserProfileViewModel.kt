package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.features.main.domain.use_case.MainUseCases
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

private const val TAG = "UserProfileViewModel"
@HiltViewModel
class UserProfileViewModel @Inject constructor(

    private val useCases: MainUseCases,
    state: SavedStateHandle,

):ViewModel() {

    private val _userToken = prefs?.accessToken

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "UserToken: $_userToken")
            useCases.getOwnProfile(token = _userToken.toString())
        }
    }


    fun onEvents(event : UserProfileEvents){

        when(event){

            UserProfileEvents.GetProfile -> TODO()
        }

    }






}