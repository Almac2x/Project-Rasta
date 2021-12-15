package com.rastatech.projectrasta.features.splash_login_signup.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.presentation.screens.BottomBar
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.OrderType
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder
import com.rastatech.projectrasta.features.splash_login_signup.presentation.login.LoginViewModel
import com.rastatech.projectrasta.features.splash_login_signup.presentation.login.LoginEvents
import com.rastatech.projectrasta.nav_graph.HOME_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.components.LoadingDialog

@Composable
fun LoginScreen(

    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()

) {
    val cardElevation = 3.dp
    val paddingCard = PaddingValues(start = 12.dp, end = 12.dp)
    val paddingCardContent = 25.dp
   // val navigate by viewModel.navigateToHomeGraph.observeAsState()

    // Loading Screen Dialog
    if(viewModel.isLoading.value){
        LoadingDialog(isVisible = viewModel.isLoading)
    }


    //Toast
    if(viewModel.hasError.value){

        // This only solves, Error Exception not 403 Errors
        Toast.makeText(LocalContext.current, "${viewModel.error.value}", Toast.LENGTH_LONG).show()

    }

    // Navigate to LoginScreen if True
    LaunchedEffect(key1 = viewModel.navigateToHomeGraph.value){

        if(viewModel.navigateToHomeGraph.value){
            navController.navigate(route = "${HOME_GRAPH_ROUTE}/" + viewModel.argument){
                popUpTo(AuthScreens.Login.route){
                    inclusive = true
                }
            }
        }

    }

    //Start of UI
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // App Logo
            Image(
                painter = painterResource(
                    id = R.drawable.title
                ),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )

            // Card Content
            Card(
                Modifier
                    .weight(2f)
                    .padding(paddingCard),
                shape = RoundedCornerShape(30.dp),
                elevation = cardElevation
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingCardContent)
                ) {
                    // Login Text
                    Text(text = "Log In", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column {
                            // Username
                            CustomTextField(
                                textState = viewModel.username,
                                hintText = "Username",
                                leadingIcon = Icons.Filled.Person
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Password
                            CustomTextField(
                                textState = viewModel.password,
                                hintText = "Password",
                                leadingIcon = Icons.Filled.Lock,
                                isPassword = true
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 25.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Checkbox( modifier = Modifier.padding(end = 10.dp),
                                    checked = viewModel.rememberMe.value,
                                    onCheckedChange = {viewModel.rememberMe.value = it}
                                )
                                Text(text = "Remember me")
                            }

                            //Spacer(modifier = Modifier.height(50.dp))

                            // Login Button
                            Button(
                                onClick = {

                                    // Gets the User to display
                                     viewModel.onEvent(LoginEvents.Login)

                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(text = "LOG IN")
                            } // Login Button

                            Spacer(modifier = Modifier.height(8.dp))



                            Spacer(modifier = Modifier.height(8.dp))

                            // Don't have an account? Sign up
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "Don't have an account?",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                TextButton(onClick = {

                                    navController.navigate(AuthScreens.SignUp.route){
                                        popUpTo(route = AuthScreens.Splash.route){
                                            inclusive = true
                                        }
                                    }
                                }) {
                                    Text(text = "Sign Up", fontWeight = FontWeight.Bold)
                                }
                            } // Row
                        }
                    }
                }
            } // Card

            Spacer(modifier = Modifier.weight(0.45f))
        } // Column
    } // Scaffold
}