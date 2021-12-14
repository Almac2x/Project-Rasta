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

    val logout = mutableStateOf(false)
    val logoutAlertDialog = mutableStateOf(false)

    private var _numberOfActiveWishes = mutableStateOf(0)
    val numberOfActiveWishes : Int
        get() = _numberOfActiveWishes.value

    private var _numberOfFulfiledWishes = mutableStateOf(0)
    val numberOfFulfiledWishes : Int
        get() = _numberOfFulfiledWishes.value

    private val _userID = mutableStateOf(0)
    val userID : Int
        get() = _userID.value


    init {
        Log.i(TAG, "Initializing")
        viewModelScope.launch(Dispatchers.Main) {
             val requestUserProfile = async{mainUseCases.getOwnProfile(token = _userToken.toString())}

            requestUserProfile.join()

            _userID.value = requestUserProfile.await().body()?.user_id?:0 // default value of 0 userID

            updateFulfilledWishes()
            updateActiveList()

           /* requestUserActiveWishList.join()
            requestUserWishListFulfilled.join()
            requestUserWishStatus.join()*/


            _firstName.value = requestUserProfile.await().body()?.first_name.toString() // kung null to string ko lang
            _lastName.value = requestUserProfile.await().body()?.last_name.toString()
            _userName.value = requestUserProfile.await().body()?.username.toString()

        }
    }

    fun updateFulfilledWishes(){

        Log.i(TAG, "Updating Fulfiled Wishes -> Start")
        viewModelScope.launch(Dispatchers.Main) {
            val requestUserWishListFulfilled = async {
                RetrofitInstance.wishApi.getAUserWishListFulfilled(
                    token = retroFitToken,
                    userID = userID ?: 2
                )
            }

            requestUserWishListFulfilled.join()
            _wishesFulfilled.value = requestUserWishListFulfilled.await().body()!!

            updateNumberStatus()

            Log.i(TAG, "Updating Fulfiled Wishes -> End")
        }

    }

    fun updateNumberStatus(){

        viewModelScope.launch(Dispatchers.Main) {

            val requestUserWishStatus = async {
                RetrofitInstance.wishApi.getWishStatus(
                    token = retroFitToken,
                    userID = userID ?: 2
                )
            }

            _numberOfActiveWishes.value = requestUserWishStatus.await().body()?.get("active_wishes")!!

            _numberOfFulfiledWishes.value = requestUserWishStatus.await().body()?.get("fulfilled_wishes")!!
        }

    }

    fun updateActiveList(){

        Log.i(TAG, "Updating Active Wishes -> Start")
        viewModelScope.launch(Dispatchers.Main) {
            // Double Check with other account
            val requestUserActiveWishList = async {
                RetrofitInstance.wishApi.getAUserActiveWishList(
                    token = retroFitToken.toString(),
                    userID = userID ?: 2
                )
            } // dapat hindi 2 default

            requestUserActiveWishList.join()
            _activeWishes.value = requestUserActiveWishList.await().body()!!

            updateNumberStatus()

            Log.i(TAG, "Updating Active Wishes -> End")
        }
    }

    fun onEvents(event : UserProfileEvents){

        when(event){

            UserProfileEvents.GetProfile -> TODO()
        }

    }






}