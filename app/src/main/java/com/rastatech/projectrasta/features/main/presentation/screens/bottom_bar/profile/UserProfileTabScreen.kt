package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.*
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs.WishListScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs.WishesFulfilled
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.Tabs

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun UserProfileTabScreen(wishList: List<String>, wishFulfilled: List<String>) {
    val tabs = listOf(
        "Wishlist",
        "Wishes Fulfilled"
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Column() {
        Tabs(tabs = tabs, pagerState = pagerState)
        ProfileTabsContent(
            wishlist = wishList,
            wishFulfilled = wishFulfilled,
            pagerState = pagerState
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ProfileTabsContent(wishlist: List<String>, wishFulfilled: List<String>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> WishListScreen(list = wishFulfilled) // ipass ang mga wish list dito
            1 -> WishesFulfilled() // ipass ang mga wish lsit dito
        }
    }
}
