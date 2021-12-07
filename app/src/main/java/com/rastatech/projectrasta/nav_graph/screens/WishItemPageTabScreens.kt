package com.rastatech.projectrasta.nav_graph.screens

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */
sealed class WishItemPageTabScreens(
    val route: String,
    val title: String
) {
    object Reason: WishItemPageTabScreens(
        route = "reason",
        title = "Reason"
    )
    object Donors: WishItemPageTabScreens(
        route = "donors",
        title = "Donors"
    )
}
