package com.rastatech.projectrasta.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.HomeViewModel
import com.rastatech.projectrasta.ui.components.WishList


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(

    viewModel:HomeViewModel = hiltViewModel(),
    navController: NavController
){


    WishList(wishEntities = viewModel.allWishes)

}
