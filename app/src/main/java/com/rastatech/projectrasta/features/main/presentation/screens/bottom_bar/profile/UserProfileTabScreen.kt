package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.*
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
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
fun UserProfileTabScreen(viewModel: UserProfileViewModel) {
    val tabs = listOf(
        "Wishlist",
        "Wishes Fulfilled"
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Column() {
        Tabs(tabs = tabs, pagerState = pagerState)
        ProfileTabsContent(
            wishlist = viewModel.activeWishes,
            wishFulfilled = viewModel.wishesFulfilled,
            pagerState = pagerState
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ProfileTabsContent(wishlist: List<WishDTO>, wishFulfilled: List<WishDTO>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> WishListScreen(list = wishlist) // ipass ang mga wish list dito
            1 -> WishesFulfilled(wishList = wishFulfilled) // ipass ang mga wish lsit dito
        }
    }
}
