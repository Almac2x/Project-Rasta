package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
 * @param textState state of the text field
 * @param hintText hint text to be displayed
 * @param leadingIcon icon to be displayed at the start of the text field
 * @param isPassword enables/disables text field password
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
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Ascii, imeAction = ImeAction.Done),
        visualTransformation = if (!isPasswordVisible.value && !isPassword) VisualTransformation.None else PasswordVisualTransformation(),
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

@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    labelFontSize: TextUnit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column {
        Text(text = label, fontSize = labelFontSize)

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    val input = remember { mutableStateOf(TextFieldValue()) }

    CustomTextField(
        value = input.value,
        onValueChange = {input.value = it},
        modifier = Modifier.fillMaxWidth(),
        label = "Gems",
        keyboardType = KeyboardType.Number,
        labelFontSize = 15.sp
    )
}