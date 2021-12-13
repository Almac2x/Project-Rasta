package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/12/2021
 */

private const val TAG = "WishItemPageViewModel"
@HiltViewModel
class WishItemPageViewModel@Inject constructor(
    state: SavedStateHandle,
    private val useCases: WishUseCases
): ViewModel() {
    val wishName =  mutableStateOf(TextFieldValue())
    val reason =  mutableStateOf(TextFieldValue())
    val imageURL =   mutableStateOf(TextFieldValue())
    val gemsRequired =  mutableStateOf(TextFieldValue())

    init {

    }
}

