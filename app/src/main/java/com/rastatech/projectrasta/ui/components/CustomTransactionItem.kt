package com.rastatech.projectrasta.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rastatech.projectrasta.features.main.data.remote.dto.TransactionDTO
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions.TransactionViewModel
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.rastatech.projectrasta.utils.Convert

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/11/2021
 */

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomTransactionItem(
    modifier: Modifier = Modifier,
    transactionDTO: TransactionDTO
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = CardCornerRadius.small
    ) {
        val amountSymbol = transactionDTO.amount.first()
        val amount = transactionDTO.amount.substring(1, transactionDTO.amount.lastIndex + 1).toInt()

        Box(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                    Text(
                        text = transactionDTO.transaction_details,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = transactionDTO.transaction_date,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = transactionDTO.transaction_time,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (amountSymbol == '+') Color.Green else Color.Red)
                                ) {
                                    append("$amountSymbol")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                ) {
                                    append(Convert.toCompactNumber(amount = amount))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = false)
@Composable
private fun Preview() {
    CustomTransactionItem(
        modifier = Modifier.fillMaxWidth(),
        transactionDTO = TransactionDTO(
            amount = "-500",
            transaction_date = "12/12/21",
            transaction_details = "Robbed",
            transaction_time = "12:12"
        )
    )
}