package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@Composable
fun TransactionScreen() {
    val list = listOf("A", "B", "C", "D")

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list.size) { index ->
            Row(Modifier.fillMaxWidth().padding(10.dp)) {
                CustomDonatorsItem(name = list[index], username = "christian", gems = 300)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TransactionScreen()
}