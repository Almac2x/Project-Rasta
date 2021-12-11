package com.rastatech.projectrasta.features.main.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

sealed class TransactionType(
    open val value: String,
    open var amount: Int,
    open val icon: ImageVector
) {
    data class Donate(
        override val value: String,
        override var amount: Int,
        override val icon: ImageVector
        ): TransactionType(
        value = "Donate",
        amount = 0,
        icon = Icons.Default.Add
    )

    data class Add(
        override val value: String,
        override var amount: Int,
        override val icon: ImageVector
    ): TransactionType(
        value = "Add",
        amount = 0,
        icon = Icons.Default.Add
    )

    data class Send(
        override val value: String,
        override var amount: Int,
        override val icon: ImageVector
    ): TransactionType(
        value = "Send",
        amount = 0,
        icon = Icons.Default.Remove
    )
}