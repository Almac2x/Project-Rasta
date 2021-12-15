package com.rastatech.projectrasta.features.main.data.remote.dto


data class TransactionDTO(
    val amount : String,
    val transaction_date : String,
    val transaction_details: String,
    val transaction_time: String
)