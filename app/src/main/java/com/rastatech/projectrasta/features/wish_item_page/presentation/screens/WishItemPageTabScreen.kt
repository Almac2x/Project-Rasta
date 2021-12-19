package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.rastatech.projectrasta.features.main.data.remote.dto.CommentsDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.DonatorDTO
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.CommentsTabScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.DonatorsTabScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs.ReasonTabScreen
import kotlinx.coroutines.launch

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun WishItemPageTabScreen(reason: String, donators: List<DonatorDTO>, comments: List<CommentsDTO>,
                          onComment: (String)->Unit) {
    val tabs = listOf(
        "Reason",
        "Donators",
        "Comments"
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Column() {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(
            onComment = onComment,
            reason = reason,
            list = donators,
            pagerState = pagerState,
            comments = comments
        )
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<String>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Color.White
            )
        },
        indicator = {
            tabPositions -> run {
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 3.dp,
                    color = Color.Blue
                )
            }
        }
    ) {
        tabs.forEachIndexed {index, _ ->
            Tab(
                text = {
                    Text(
                        tabs[index],
                        color = if (pagerState.currentPage == index) Color.Blue else Color.Black
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun TabsContent(reason: String, list: List<DonatorDTO>, comments: List<CommentsDTO>,
                pagerState: PagerState,onComment: (String)->Unit) {
    Log.i(pagerState.currentPage.toString(), pagerState.currentPage.toString())
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> ReasonTabScreen(content = reason)
            1 -> DonatorsTabScreen(list = list)
            2 -> CommentsTabScreen(comments = comments, onComment = onComment)

        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
   // WishItemPageTabScreen(reason = "", donators = listOf())
}
