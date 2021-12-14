package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.add_update_wish.util.WishProcess
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
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



    private val retroFitToken = "Bearer "+SecretRastaApp.prefs?.accessToken?:""
    private val token = SecretRastaApp.prefs?.accessToken?:""

    private val _wishID = state.get<Int>(NavigationKey.WishID.value)

     val wishName =  mutableStateOf(TextFieldValue())

     val reason =  mutableStateOf(TextFieldValue())

     val imageURL =   mutableStateOf(TextFieldValue())

     val gemsRequired =  mutableStateOf(TextFieldValue())

     private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String>
        get() = _toastMessage

    private var _navigateUp = mutableStateOf(false)
    val navigateUp: Boolean
        get() = _navigateUp.value

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean>
        get() = _showToast

    init {
        Log.i(TAG,"Initializing")
        Log.i(TAG,"WishID = $_wishID" )

        if(_wishID != null){

            viewModelScope.launch (Dispatchers.IO){
                val requestWish = async{RetrofitInstance.wishApi.getWish(token = retroFitToken, wishID = _wishID)}

                requestWish.join()

                val wish = requestWish.await().body()

                wishName.value = TextFieldValue(wish?.wish_name ?: "")
                reason.value = TextFieldValue(wish?.description ?: "")
                gemsRequired.value = TextFieldValue(wish?.rastagems_required.toString() ?: "")
                imageURL.value = TextFieldValue(wish?.image_url ?: "")

            }
        }

    }

    fun onEvent(event : AddUpdateWishEvents){
        when(event){

            is AddUpdateWishEvents.AddWish ->{

              val evenRequest =  viewModelScope.launch(Dispatchers.IO) {
                    val requestAddWish = async{ useCases.createAWish(
                        token = token?:"",
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
            is AddUpdateWishEvents.UpdateWish -> {
                val evenRequest =  viewModelScope.launch(Dispatchers.IO) {

                    val requestUpdateWish  = async{RetrofitInstance.wishApi.updateAWish( token = retroFitToken?:"", wishID = _wishID?:0,
                        createWish = CreateWishRequestDTO(
                        description = reason.value.text,
                        image_url = imageURL.value.text,
                        wish_name = wishName.value.text,
                        rastagems_required = gemsRequired.value.text.toInt()
                        )
                    )}

                    when (requestUpdateWish.await().code()){


                        200 ->{
                            _showToast.value = true
                            _toastMessage.value = "Update Wish Successful"
                            _navigateUp.value = true
                        }
                        else ->{
                            _showToast.value = true
                            _toastMessage.value ="Something Went Wrong"
                        }
                    }
                    delay(1000L)
                    _toastMessage.value = ""
                    _showToast.value = false

                }

            }
        }
    }
}