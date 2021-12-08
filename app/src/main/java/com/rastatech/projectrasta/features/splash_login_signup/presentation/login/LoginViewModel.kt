package com.rastatech.projectrasta.features.splash_login_signup.presentation.login


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

                is LoginEvents.Login ->{


                   viewModelScope.launch(Dispatchers.Main){
                        _isLoading.value = true

                       var  response : Response<TokenDTO>? = null

                       Log.i(TAG, "${response?.code().toString()}")


                            val job = launch {
                               response = userUseCases.getLoginTokenApiRequest(
                                   username = username.value.text, // add here the Text Fields For UserName
                                   password = password.value.text // add here the TextFields for Password
                               )

                           }

                        job.join()

                       Log.i(TAG, response?.code().toString())
                           if (response?.code() == 200){

                               _argument = response?.body()?.access_token

                               _navigateToHomeGraph.value = true
                               Log.i(TAG, "_navigationToHomeGraph Value : ${navigateToHomeGraph.value}")

                           }
                            _isLoading.value = false
                       }

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