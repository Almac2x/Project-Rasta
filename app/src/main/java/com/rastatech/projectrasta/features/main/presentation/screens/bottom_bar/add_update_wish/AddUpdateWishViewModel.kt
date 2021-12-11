package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "AddUpdateWishViewModel"
@HiltViewModel
class AddUpdateWishViewModel @Inject constructor(
    state: SavedStateHandle,
    private val useCases: WishUseCases
):ViewModel() {


    private val userToken = state.get<String>("access_token") ?: ""

     val wishName =  mutableStateOf(TextFieldValue())
     val reason =  mutableStateOf(TextFieldValue())
     val imageURL =   mutableStateOf(TextFieldValue())


   // val gems =  mutableStateOf(TextFieldValue(if (gemsRequired == 0) "" else gemsRequired.toString()))
   private val gemsRequired =  mutableStateOf(TextFieldValue())

    init {
        viewModelScope.launch (Dispatchers.IO){
            Log.i(TAG, "UserToken: $userToken")

        }
    }

    fun onEvent(event : AddUpdateWishEvents){

        when(event){

            is AddUpdateWishEvents.AddWish ->{

                viewModelScope.launch(Dispatchers.IO) {

                    val request = useCases.createAWish(token = userToken, description =reason.value.text,
                                                                                imageURL = imageURL.value.text, wishName = wishName.value.text,
                                                                                rastaGemsRequired =gemsRequired.value.text.toInt() )
                }

            }
            is AddUpdateWishEvents.UpdateWish -> TODO()
        }

    }




}