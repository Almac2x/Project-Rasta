package com.rastatech.projectrasta.features.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rastatech.projectrasta.features.splash_login_signup.presentation.signup.SignUpEvents
import com.rastatech.projectrasta.features.splash_login_signup.presentation.signup.SignUpViewModel
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.components.LoadingDialog
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

@Composable
fun SignUpScreen(

    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()

) {


    val cardElevation = 3.dp
    val paddingCard = 12.dp
    val paddingCardContent = 15.dp


    Scaffold(backgroundColor = AppColorPalette.primary) {
        // Loading Screen Dialog
        if(viewModel.isLoading.value){
            LoadingDialog(isVisible = viewModel.isLoading)
        }

        Card(
            modifier = Modifier
                .padding(paddingCard)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = CardCornerRadius.large,
            elevation = cardElevation
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingCardContent)
            ) {
                // Arrow Back and Sign Up Text
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    IconButton(onClick = {

                        navController.navigate(AuthScreens.Login.route){
                        popUpTo(route = AuthScreens.Login.route){
                            inclusive = true
                        }
                    }

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Return to Login",
                            Modifier.size(30.dp)
                        )
                    }

                    // Sign Up Text
                    Text(
                        text = "Sign Up",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                } // Row

                // Text Fields
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    // First Name
                    CustomTextField(
                        textState = viewModel.firstName,
                        hintText = "First Name",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Last Name
                    CustomTextField(
                        textState = viewModel.lastName,
                        hintText = "Last Name",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Username
                    CustomTextField(
                        textState = viewModel.userName,
                        hintText = "Username",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Phone Number
                    CustomTextField(
                        textState = viewModel.phoneNumber,
                        hintText = "Phone Number",
                        leadingIcon = Icons.Filled.ContactPhone
                    )

                    // Email
                    CustomTextField(
                        textState = viewModel.email,
                        hintText = "Email",
                        leadingIcon = Icons.Filled.Email
                    )

                    // Password
                    CustomTextField(
                        textState = viewModel.password,
                        hintText = "Password",
                        leadingIcon = Icons.Filled.Lock,
                        isPassword = true
                    )

                    // Verify Password
                    CustomTextField(
                        textState = viewModel.verifyPassword,
                        hintText = "Verify Password",
                        leadingIcon = Icons.Filled.Lock,
                        isPassword = true
                    )

                    // Sign Up Button
                    Button(
                        onClick = {

                            viewModel.onEvent(SignUpEvents.AddUser)


                            }, // Pass an event to the viewmodel

                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AppColorPalette.error,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "SIGN UP")
                    } // Sign Up Button

                    // Already have an account? Log in
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Already have an account?",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        TextButton(onClick = {

                                        navController.navigate(AuthScreens.Login.route){
                                        popUpTo(AuthScreens.Login.route){
                                            inclusive = true
                                        }
                        } }) {
                            Text(text = "Log in", fontWeight = FontWeight.Bold)
                        }
                    } // Row
                }
            }
        }
    } // Scaffold
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

}