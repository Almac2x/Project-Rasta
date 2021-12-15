package com.rastatech.projectrasta.features.splash_login_signup.presentation.login


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.TokenDTO
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.OrderType
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder
import com.rastatech.projectrasta.features.splash_login_signup.presentation.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import javax.inject.Inject
import retrofit2.Response


const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel@Inject constructor(

    private val userUseCases: UserUseCases

):ViewModel(){

    //For Visibility of Loading Screen
    private val _isLoading = mutableStateOf(false)
    val isLoading : State<Boolean>
        get() = _isLoading

    private val _error = mutableStateOf("")
    val error : State<String>
        get() = _error

    private val _hasError = mutableStateOf(false)
    val hasError : State<Boolean>
        get() = _hasError

    private val _navigateToHomeGraph = mutableStateOf(false)
    val navigateToHomeGraph : State<Boolean>
        get() = _navigateToHomeGraph

    private val _state = mutableStateOf<UserState>(UserState())
    val state: State<UserState> = _state

    private var recentlyDeletedUser: UserEntity? = null // this is for restoring the recently deleted user in the login screen

    private var getUserJob: Job? = null //keeps track of the flow Job

    private var _argument : String? = null
    val argument: String?
    get() = _argument

    var rememberMe = mutableStateOf(false) // initialize this
    val username = mutableStateOf(TextFieldValue()) // username textfield
    val password =  mutableStateOf(TextFieldValue()) // password textfield

    init {
        getUsers(UserOrder.FirstName(OrderType.Ascending)) // initialize the list with this UserOrder at initialization
    }

    /**
     * Here is where we add the events we created to call the useCases inside when
     *
     * @param event
     */

    fun onEvent(event : LoginEvents){

        when(event){

            //Add Try catch here so it wont crash when it timeouts or No internet or Wrong URL
                is LoginEvents.Login ->{

                    _isLoading.value = true

                   viewModelScope.launch(Dispatchers.IO){

                       var  response : Response<TokenDTO>? = null
                       var job : Job? = null

                       withTimeout(5000L) {

                           try {

                               response = userUseCases.getLoginTokenApiRequest(
                                   username = username.value.text, // add here the Text Fields For UserName
                                   password = password.value.text // add here the TextFields for Password
                               )

                           } catch (e: Exception) {

                               Log.i(TAG, e.message.toString())
                               _error.value = e.message.toString()
                               _hasError.value = true

                           } finally {
                               delay(1000L)

                               prefs?.accessToken = response?.body()?.access_token // saves the token locally

                               Log.i(TAG, "Remember Me: ${rememberMe.value}")
                               prefs?.rememberMe = rememberMe.value

                               Log.i(TAG, response?.code().toString())
                               if (response?.code() == 200) {

                                   _argument = response?.body()?.access_token

                                   _navigateToHomeGraph.value = true // Di ko alam kung sa finally ito
                                   Log.i(TAG, "_navigationToHomeGraph Value : ${navigateToHomeGraph.value}")
                               }

                               _error.value = ""
                               _hasError.value = false
                               _isLoading.value = false
                           }


                       }
                       _isLoading.value = false

                      //job?.join()
                       } // End Coroutine

                }

                is LoginEvents.Order ->{

                    //Checks if the User order is the same as the User order we want to change it to AND same with the orderType if it is the same
                    // their is a ::class to check if the classes being compared are the same
                    if (state.value.userOrder::class == event.userOrder::class
                        && state.value.userOrder.orderType == event.userOrder.orderType){
                        return
                    }

                    getUsers(userOrder = event.userOrder)


                }
                is LoginEvents.DeleteLogin ->{

                    viewModelScope.launch {
                        userUseCases.deleteUser(event.user) // uses the delete user UseCase to delete user from database
                        recentlyDeletedUser = event.user
                    }

                }
                // Restores the recently deleted user back into the database
                is LoginEvents.RestoreLogin ->{

                    viewModelScope.launch {

                        userUseCases.addUser(recentlyDeletedUser ?: return@launch) // returns a launch if the recentlyDeletedUser is Empty
                        recentlyDeletedUser = null
                    }

                }
                is LoginEvents.ToggleOrderSection ->{

                    _state.value = state.value.copy(  // makes a copy then change the value inside the state
                        isOrderSectionVisible = !state.value.isOrderSectionVisible
                    )

                }

        }
    }


    private fun getUsers(userOrder: UserOrder){

        getUserJob?.cancel() // cancels the job to prevent a new instance of Flow each time this function is called

         getUserJob = userUseCases.getAllUsers(userOrder = userOrder) // will return a Flow from getAllUsers UseCase
            .onEach { users ->

                _state.value = state.value.copy(
                    users = users,
                    userOrder = userOrder
                )

            }
            .launchIn(viewModelScope)
    }

}