package com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.main.data.remote.dto.DonatorDTO
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@Composable
fun DonatorsTabScreen(list: List<DonatorDTO>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = list){  donator ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                CustomDonatorsItem(name =donator.donator_full_name , username = donator.donator_username,
                    gems = donator.amount)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DonatorsTabScreen(list = listOf())
}