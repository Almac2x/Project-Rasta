package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.rastatech.projectrasta.ui.theme.TextFieldCornerRadius

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/02/2021
 */


/**
 * Custom Text Field
 *
 * @param textState text field text state
 * @param hintText text field hint text
 * @param leadingIcon leading icon for text field
 * @param isPassword enables text field password
 */
@Composable
fun CustomTextField(
    textState: MutableState<TextFieldValue>,
    hintText: String,
    leadingIcon: ImageVector,
    isPassword: Boolean = false
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        onValueChange = { textState.value = it },
        shape = TextFieldCornerRadius.large,
        label = {
            Text(text = hintText)
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = "$hintText Icon")
        },
        trailingIcon = {
            if (isPassword) IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                Icon(
                    imageVector = if(isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "Password Visibility"
                )
            }
        }
    )
}