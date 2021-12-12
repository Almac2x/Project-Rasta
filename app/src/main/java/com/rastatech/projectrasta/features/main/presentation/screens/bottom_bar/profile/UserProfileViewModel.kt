package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.use_case.MainUseCases
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserProfileViewModel"
@HiltViewModel
class UserProfileViewModel @Inject constructor(

    private val mainUseCases: MainUseCases,
    private val wishUseCases: WishUseCases,
    state: SavedStateHandle,

):ViewModel() {

    private val _userToken = prefs?.accessToken

    private val retroFitToken = "Bearer $_userToken"

    private var _firstName = mutableStateOf("")
    val firstName: String
        get() = _firstName.value
    private val _lastName= mutableStateOf("")
    val lastName: String
        get() = _lastName.value
    private val _userName= mutableStateOf("")
    val userName: String
        get() = _userName.value

    private val _balance= mutableStateOf(0)
    val balance: Int
        get() = _balance.value

    private val _activeWishes   = mutableStateOf(emptyList<WishDTO>())
    val activeWishes : List<WishDTO>
        get() = _activeWishes.value

    private val _wishesFulfilled = mutableStateOf(emptyList<WishDTO>())
    val wishesFulfilled : List<WishDTO>
        get() = _wishesFulfilled.value


    init {

        viewModelScope.launch(Dispatchers.Main) {
            Log.i(TAG, "UserToken: $_userToken")
             val requestUserProfile = async{mainUseCases.getOwnProfile(token = _userToken.toString())}

            val userID = requestUserProfile.await().body()?.user_id
            requestUserProfile.join()

            // Double Check with other account
            val requestUserActiveWishList = async { RetrofitInstance.wishApi.getAUserActiveWishList(token = retroFitToken.toString(), userID =userID?:2 )} // dapat hindi 1 default

            val requestUserWishListFulfilled = async { RetrofitInstance.wishApi.getAUserWishListFulfilled(token = retroFitToken, userID =userID?:2 ) }

            requestUserActiveWishList.join()
            requestUserWishListFulfilled.join()

            _activeWishes.value = requestUserActiveWishList.await().body()!!
            _wishesFulfilled.value = requestUserWishListFulfilled.await().body()!!


            _firstName.value = requestUserProfile.await().body()?.first_name.toString() // kung null to string ko lang
            _lastName.value = requestUserProfile.await().body()?.last_name.toString()
            _userName.value = requestUserProfile.await().body()?.username.toString()



        }
    }


    fun onEvents(event : UserProfileEvents){

        when(event){

            UserProfileEvents.GetProfile -> TODO()
        }

    }






}