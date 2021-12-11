package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.components.WishList

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun WishesFulfilled() {
    var wishEntities: List<WishEntity> = listOf(
        WishEntity(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
            user_id = "121", image = R.drawable.gift),
        WishEntity(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
            user_id = "121", image = R.drawable.gift)
    )
    wishEntities = wishEntities+wishEntities
    wishEntities = wishEntities+wishEntities
    Box(modifier = Modifier.fillMaxSize()) {
        WishList(wishEntities = wishEntities)
    }
}