package com.rastatech.projectrasta.features.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/01/2021
 */

class Login: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectRastaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }
    }
}

/**
 * Login Screen
 */
@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val cardElevation = 3.dp

    val paddingCard = PaddingValues(start = 12.dp, end = 12.dp)
    val paddingCardContent = 25.dp

    val usernameText = remember { mutableStateOf(TextFieldValue()) }
    val passwordText = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // App Logo
            Image(
                painter = painterResource(
                    id = R.drawable.ic_launcher_foreground
                ),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(0.5f)
                    .size(128.dp),
                colorFilter = ColorFilter.tint(Color.White)
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
                                textState = usernameText,
                                hintText = "Username",
                                leadingIcon = Icons.Filled.Person
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Password
                            CustomTextField(
                                textState = passwordText,
                                hintText = "Password",
                                leadingIcon = Icons.Filled.Lock,
                                isPassword = true
                            )

                            Spacer(modifier = Modifier.height(50.dp))

                            // Login Button
                            Button(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(text = "LOG IN")
                            } // Login Button

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
                                TextButton(onClick = { }) {
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


