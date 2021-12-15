package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem
import com.rastatech.projectrasta.ui.components.CustomTransactionItem
import com.rastatech.projectrasta.ui.theme.AppColorPalette

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

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = AppColorPalette.background) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Transactions",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    ) {
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
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    TransactionScreen()
}