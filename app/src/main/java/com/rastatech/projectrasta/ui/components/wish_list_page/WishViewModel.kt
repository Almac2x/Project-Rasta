package com.rastatech.projectrasta.ui.components.wish_list_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "WishViewModel"
@HiltViewModel
class WishViewModel@Inject constructor(


):ViewModel() {

    private val _userToken = SecretRastaApp.prefs?.accessToken
    private val retroFitToken = "Bearer $_userToken"

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String>
        get() = _toastMessage

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean>
        get() = _showToast


    fun onEvent (event : WishPageEvents ){

        when (event){

            is WishPageEvents.DeleteWish ->{

                val eventRequest = viewModelScope.launch(Dispatchers.IO) {

                    val requestDeleteGem=  async{RetrofitInstance.wishApi.deleteAWish(
                                                                     token = retroFitToken,
                                                                      wishID = event.wishID)}

                    requestDeleteGem.join()

                    when (requestDeleteGem.await().code()){

                        200 ->{
                            _showToast.value = true
                            _toastMessage.value = "Add Successful"
                        }
                        else ->{
                            _showToast.value = true
                            _toastMessage.value ="Something Went Wrong"
                        }
                    }

                    delay(1000L)

                    _showToast.value = false
                    _toastMessage.value = ""

                }

            }
        }



    }

}

