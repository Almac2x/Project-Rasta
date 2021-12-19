package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.use_case.MainUseCases
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(

    state: SavedStateHandle,
    private val useCases: WishUseCases,
    private val mainUseCases: MainUseCases

):ViewModel() {


    private val _userToken = prefs?.accessToken
    private val userToken = state.get<String>(NavigationKey.AccessToken.value) ?: ""

    private val _userID = mutableStateOf( 1 )
    val userID : Int?
        get() = _userID.value

    val query =  mutableStateOf(TextFieldValue()) // password textfield
    val direction = mutableStateOf("desc")

    private var _allWishes   = mutableStateOf(emptyList<WishDTO>())
    val allWishes : List<WishDTO>
        get() = _allWishes.value

    val retrofitToken = "Bearer " + prefs?.accessToken

    init {

        Log.i(TAG, "Initializing")
        Log.i(TAG, "Remember Me: ${prefs?.rememberMe}")

             updateList()
            getOwnProfile()
        }

    private fun getOwnProfile (){

        viewModelScope.launch(Dispatchers.IO) {
            val requestUserProfile = async{mainUseCases.getOwnProfile(token = _userToken.toString())}

            requestUserProfile.join()

            _userID.value = requestUserProfile.await().body()?.user_id?:0 // default value of 0 userID

        }

    }

         fun updateList(){

             Log.i(TAG," List Updating")
            viewModelScope.launch (Dispatchers.Main){
                Log.i(TAG, "UserToken: $userToken")
                val nani = useCases.getHomeScreenWishes(token = userToken)

                val requestAllWishes = async{RetrofitInstance.wishApi.getHomeScreenWishes(token = retrofitToken)}
                requestAllWishes.join()

                _allWishes.value = requestAllWishes.await().body()!!

            }
             Log.i(TAG," List Updated")
        }



    fun onEvent(event : HomeEvents){

        when(event){

            is HomeEvents.GetWishes ->{

            }
            is HomeEvents.GetFilteredWishes -> {
                viewModelScope.launch (Dispatchers.IO){

                    val requestAllWishes = async{RetrofitInstance.wishApi.getFilteredWishes(token = retrofitToken,
                                                sort = event.sort?:"", direction = event.direction?:"desc")}

                    requestAllWishes.join()
                    _allWishes.value = requestAllWishes.await().body()?: emptyList<WishDTO>()

                }
            }
            is HomeEvents.Search -> {

                viewModelScope.launch (Dispatchers.IO){

                    val requestSearchWish = async{RetrofitInstance.wishApi.getSearchWish(token = retrofitToken, search = query.value.text)}

                    requestSearchWish.join()
                    _allWishes.value = requestSearchWish.await().body()?: emptyList<WishDTO>()

                }


            }
            HomeEvents.GetLikedWishes -> {

                viewModelScope.launch (Dispatchers.IO){

                    val requestSearchWish = async{RetrofitInstance.wishApi.getWishLikedByAUser(token = retrofitToken, userID = _userID.value)}

                    requestSearchWish.join()
                    _allWishes.value = requestSearchWish.await().body()?: emptyList<WishDTO>()

                }

            }
        }

    }



}