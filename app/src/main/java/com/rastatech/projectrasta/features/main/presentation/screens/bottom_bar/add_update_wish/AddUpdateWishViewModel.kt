package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "AddUpdateWishViewModel"
@HiltViewModel
class AddUpdateWishViewModel @Inject constructor(
    state: SavedStateHandle,
    private val useCases: WishUseCases
):ViewModel() {
    private val retroFitToken = SecretRastaApp.prefs?.accessToken

    val wishName =  mutableStateOf(TextFieldValue())
    val reason =  mutableStateOf(TextFieldValue())
    val imageURL =   mutableStateOf(TextFieldValue())
    val gemsRequired =  mutableStateOf(TextFieldValue())

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String>
        get() = _toastMessage

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean>
        get() = _showToast

    init {
        viewModelScope.launch (Dispatchers.IO){
            Log.i(TAG, "UserToken: $retroFitToken")
        }
    }

    fun onEvent(event : AddUpdateWishEvents){
        when(event){
            is AddUpdateWishEvents.InitProcess -> {
                if (event.type == WishProcess.Update) {
                    // initialize text fields
                    wishName.value = TextFieldValue("WishName")
                }
            }
            is AddUpdateWishEvents.AddWish ->{

              val evenRequest =  viewModelScope.launch(Dispatchers.IO) {
                    val requestAddWish = async{ useCases.createAWish(
                        token = retroFitToken?:"",
                        description = reason.value.text,
                        imageURL = imageURL.value.text,
                        wishName = wishName.value.text,
                        rastaGemsRequired = gemsRequired.value.text.toInt()
                    )}

                    when (requestAddWish.await().code()){

                        200 ->{
                            _showToast.value = true
                            _toastMessage.value = "Add Wish Successful"
                        }
                        else ->{
                            _showToast.value = true
                            _toastMessage.value ="Something Went Wrong"
                        }
                    }

                  delay(1000L)
                }
                _toastMessage.value = ""
                _showToast.value = false

            }
            is AddUpdateWishEvents.UpdateWish -> TODO()
        }
    }
}