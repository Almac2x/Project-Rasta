package com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.features.main.data.local.WishEntity
import com.rastatech.projectrasta.features.transactions.TransactionScreen
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@Composable
fun DonatorsTabScreen(list: List<WishEntity>) {
    val list = listOf("A", "B", "C", "D")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list.size) { index ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                CustomDonatorsItem(name = list[index], username = "christian", gems = 300)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DonatorsTabScreen(list = listOf())
}