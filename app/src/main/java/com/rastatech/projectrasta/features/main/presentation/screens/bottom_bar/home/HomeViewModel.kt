package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(

    state: SavedStateHandle,
    private val useCases: WishUseCases

):ViewModel() {


    var userToken = state.get<String>("access_token") ?: ""

    init {
        viewModelScope.launch (Dispatchers.IO){
            Log.i(TAG, "UserToken: $userToken")
            val nani = useCases.getHomeScreenWishes(token = userToken)
        }


    }


    fun onEvent(event : HomeEvents){

        when(event){

            is HomeEvents.GetWishes ->{

            }

        }

    }



}