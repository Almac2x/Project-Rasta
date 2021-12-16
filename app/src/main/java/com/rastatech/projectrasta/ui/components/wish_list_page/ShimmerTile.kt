package com.rastatech.projectrasta.ui.components.wish_list_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.ui.ShimmerAnimation
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

@Composable
fun ShimmerTile(){

    val tileHeight = 300.dp
    val tileElevation = 5.dp


    Card(
        modifier = androidx.compose.ui.Modifier
            .height(tileHeight)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        shape = CardCornerRadius.small,
        elevation = tileElevation){

        Box(modifier = Modifier.fillMaxSize()){
            ShimmerAnimation()
        }

    }


}