package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home.Sort
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/15/2021
 */

@ExperimentalFoundationApi
@Composable
fun CustomSortItem(sortItem: Sort, onClick: () -> Unit) {
    Card(
        backgroundColor = AppColorPalette.background,
        modifier = Modifier
            .size(80.dp)
            .combinedClickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp),
                tint = sortItem.tint,
                imageVector = sortItem.icon,
                contentDescription = sortItem.title
            )
            Text(
                color = Color.Black,
                textAlign = TextAlign.Center,
                text = sortItem.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = false)
@Composable
private fun Preview() {
    CustomSortItem(sortItem = Sort.Recent, onClick = {})
}