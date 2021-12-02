package com.rastatech.projectrasta.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
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

    /**
     * Login Screen
     */
    @Preview(showBackground = true)
    @Composable
    private fun LoginScreen() {

        val cardElevation = 3.dp

        val paddingCard = PaddingValues(start = 12.dp, end = 12.dp)
        val paddingCardContent = 25.dp

        val usernameText = remember { mutableStateOf(TextFieldValue()) }
        val passwordText = remember { mutableStateOf(TextFieldValue()) }
        val isPasswordVisible = remember { mutableStateOf(false) }

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

                // Content
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
                                UsernameTextField(textState = usernameText)
                                if (isPasswordVisible.value) Text(text = "Error")
                                Spacer(modifier = Modifier.height(8.dp))
                                PasswordTextField(
                                    textState = passwordText,
                                    isPasswordVisible = isPasswordVisible
                                )
                                if (isPasswordVisible.value) Text(text = "Error")

                                Spacer(modifier = Modifier.height(50.dp))

                                // Login Button
                                Button(
                                    onClick = { },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(text = "LOG IN")
                                }

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
                                }
                            }
                        }
                    }
                } // Card

                Spacer(modifier = Modifier.weight(0.45f))
            }
        }
    }

    /**
     * Username Text Field
     *
     * @param textState username text state
     */
    @Composable
    private fun UsernameTextField(textState: MutableState<TextFieldValue>) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState.value,
            onValueChange = { textState.value = it },
            shape = RoundedCornerShape(16.dp),
            label = {
                Text(text = "Username")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Username Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { textState.value = TextFieldValue(text = "") }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear Icon")
                }
            }
        )
    }

    /**
     * Password Text Field
     *
     * @param textState password text state
     * @param isPasswordVisible password visibility
     */
    @Composable
    private fun PasswordTextField(textState: MutableState<TextFieldValue>, isPasswordVisible: MutableState<Boolean>) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState.value,
            onValueChange = { textState.value = it },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Password Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                    Icon(
                        imageVector = if(isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password Visibility"
                    )
                }
            }
        )
    }
}


