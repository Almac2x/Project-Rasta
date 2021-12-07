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
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

@Composable
fun SignUpScreen() {
    val cardElevation = 3.dp
    val paddingCard = 12.dp
    val paddingCardContent = 15.dp

    val firstName = remember { mutableStateOf(TextFieldValue()) }
    val lastName = remember { mutableStateOf(TextFieldValue()) }
    val userName = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val verifyPassword = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(backgroundColor = AppColorPalette.primary) {
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
                    IconButton(onClick = { /* return to login */ }) {
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
                        textState = firstName,
                        hintText = "First Name",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Last Name
                    CustomTextField(
                        textState = lastName,
                        hintText = "Last Name",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Username
                    CustomTextField(
                        textState = userName,
                        hintText = "Username",
                        leadingIcon = Icons.Filled.Person
                    )

                    // Phone Number
                    CustomTextField(
                        textState = phoneNumber,
                        hintText = "Phone Number",
                        leadingIcon = Icons.Filled.ContactPhone
                    )

                    // Email
                    CustomTextField(
                        textState = email,
                        hintText = "Email",
                        leadingIcon = Icons.Filled.Email
                    )

                    // Password
                    CustomTextField(
                        textState = password,
                        hintText = "Password",
                        leadingIcon = Icons.Filled.Lock,
                        isPassword = true
                    )

                    // Verify Password
                    CustomTextField(
                        textState = verifyPassword,
                        hintText = "Verify Password",
                        leadingIcon = Icons.Filled.Lock,
                        isPassword = true
                    )

                    // Sign Up Button
                    Button(
                        onClick = { /* Go to Home Screen? */ },
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
                        TextButton(onClick = { /* Go to Log in */ }) {
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
    SignUpScreen()
}