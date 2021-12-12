package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.features.main.domain.use_case.MainUseCases
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

private const val TAG = "UserProfileViewModel"
@HiltViewModel
class UserProfileViewModel @Inject constructor(

    private val mainUseCases: MainUseCases,
    private val wishUseCases: WishUseCases,
    state: SavedStateHandle,

):ViewModel() {

    private val _userToken = prefs?.accessToken

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

    private val _activeWishes= mutableStateOf(0)
    val activeWishes : Int
        get() = _activeWishes.value

    private val _wishesFulfilled= mutableStateOf(0)
    val wishesFulfilled : Int
        get() = _wishesFulfilled.value

    val logout = mutableStateOf(false)
    val logoutAlertDialog = mutableStateOf(false)

    init {

        viewModelScope.launch(Dispatchers.Main) {
            Log.i(TAG, "UserToken: $_userToken")
             val requestUserProfile = async{mainUseCases.getOwnProfile(token = _userToken.toString())}

            val userID = requestUserProfile.await().body()?.user_id
            requestUserProfile.join()

            val requestUserWishList = async { wishUseCases.getUserWishList(token = _userToken.toString(), userID =userID?:2 )} // dapat hindi 1 default

            _firstName.value = requestUserProfile.await().body()?.first_name.toString() // kung null to string ko lang
            _lastName.value = requestUserProfile.await().body()?.last_name.toString()
            _userName.value = requestUserProfile.await().body()?.username.toString()


            delay(1000L)
        }
    }


    fun onEvents(event : UserProfileEvents){

        when(event){

            UserProfileEvents.GetProfile -> TODO()
        }

    }






}