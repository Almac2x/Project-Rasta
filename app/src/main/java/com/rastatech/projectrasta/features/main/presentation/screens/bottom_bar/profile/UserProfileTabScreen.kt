package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs.WishListScreen
import com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile.tabs.WishesFulfilled
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.Tabs

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun UserProfileTabScreen(viewModel: UserProfileViewModel, bottomNavController: NavController, userType: UserType) {
    val tabs = listOf(
        "Wishlist",
        "Wishes Fulfilled"
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Column() {
        Tabs(tabs = tabs, pagerState = pagerState)
        ProfileTabsContent(
            userType =userType,
            wishlist = viewModel.activeWishes,
            wishFulfilled = viewModel.wishesFulfilled,
            pagerState = pagerState,
            bottomNavController = bottomNavController,
            updateActiveList = {viewModel.updateActiveList()},
            updateFulfiledList = {viewModel.updateFulfilledWishes()}

        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ProfileTabsContent(userType: UserType,wishlist: List<WishDTO>, wishFulfilled: List<WishDTO>, bottomNavController: NavController,
                       pagerState: PagerState, updateActiveList: ()-> Unit, updateFulfiledList: ()-> Unit) {
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> WishListScreen(wishList = wishlist, navController = bottomNavController, updateList = updateActiveList, userType = userType) // wishlist Active
            1 -> WishesFulfilled(wishList = wishFulfilled, navController = bottomNavController, updateList = updateFulfiledList, userType = userType) //  wishList Fulfilled
        }
    }
}
