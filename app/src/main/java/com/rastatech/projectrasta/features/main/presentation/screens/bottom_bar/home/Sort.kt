package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rastatech.projectrasta.ui.theme.AppColorPalette

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/15/2021
 */
sealed class Sort(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val tint: Color
) {
    object Upvote: Sort(
        title = "Upvote",
        value = "upvotes",
        icon = Icons.Outlined.ThumbUp,
        tint = AppColorPalette.secondary
    )

    object Downvote: Sort(
        title = "Downvote",
        value = "downvotes",
        icon = Icons.Outlined.ThumbDown,
        tint = AppColorPalette.error
    )

    object Recent: Sort(
        title = "Recent",
        value = "desc",
        icon = Icons.Outlined.AccessTime,
        tint = Color.Green
    )

    object Donated: Sort(
        title = "Donated",
        value = "rastagemsRequired",
        icon = Icons.Outlined.CardGiftcard,
        tint = Color.Magenta
    )

    object Liked: Sort(
        title = "Liked",
        value = "liked",
        icon = Icons.Outlined.ThumbUp,
        tint = Color.Red
    )
}
