package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


const val TAG ="MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val state : SavedStateHandle

    // add here the userCases for this feature
):ViewModel(){

    init {
        val userToken = state.get<String>("access_token")
        Log.i(TAG, "UserToken: "+ userToken.toString())
    }



}