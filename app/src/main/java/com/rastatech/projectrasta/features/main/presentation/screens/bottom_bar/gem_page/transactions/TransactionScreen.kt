package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem
import com.rastatech.projectrasta.ui.components.CustomTransactionItem

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val transactions = viewModel.transactions

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(transactions.size) { index ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                CustomTransactionItem(
                    transactionDTO = transactions[index]
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    TransactionScreen()
}