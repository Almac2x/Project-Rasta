package com.rastatech.projectrasta.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.models.wish.Wish
import com.rastatech.projectrasta.ui.theme.components.WishList


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(){

    var wishes: List<Wish> = listOf(
        Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift),
        Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift)
    )
    wishes = wishes+wishes
    wishes = wishes+wishes

    WishList(wishes = wishes)

}
