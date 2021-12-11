package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
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


    private val userToken = state.get<String>(NavigationKey.AccessToken.value) ?: ""

    val token = "Bearer " + prefs?.accessToken

    init {

        Log.i(TAG, "PrefToken: ${prefs?.accessToken}")
        viewModelScope.launch (Dispatchers.IO){
            Log.i(TAG, "UserToken: $userToken")
            val nani = useCases.getHomeScreenWishes(token = userToken)

            RetrofitInstance.wishApi.getHomeScreenWishes(token = token)
            RetrofitInstance.wishApi.getWish(token = token, wishID = 1)

            RetrofitInstance.wishApi.getAUserActiveWishList(token = token, userID = 2 )
            RetrofitInstance.wishApi.getAUserWishListDonated(token = token, userID = 2)
            RetrofitInstance.wishApi.getAUserWishListFulfilled(token = token, userID = 2)

            RetrofitInstance.mainApi.getUserBalance(token = token)

        }


    }

    fun onEvent(event : HomeEvents){

        when(event){

            is HomeEvents.GetWishes ->{

            }

        }

    }



}