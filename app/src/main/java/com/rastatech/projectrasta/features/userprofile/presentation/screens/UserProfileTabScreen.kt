package com.rastatech.projectrasta.features.userprofile.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.rastatech.projectrasta.features.userprofile.presentation.screens.tabs.WishListScreen
import com.rastatech.projectrasta.features.userprofile.presentation.screens.tabs.WishesFulfilled
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.Tabs
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.TabsContent
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.DonatorsTabScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.ReasonTabScreen
import kotlinx.coroutines.launch

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
            0 -> WishListScreen(list = wishFulfilled)
            1 -> WishesFulfilled()
        }
    }
}
