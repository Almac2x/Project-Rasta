package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.nav_graph.WishGraph
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.theme.AppColorPalette


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(

    viewModel:HomeViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    /**
     * TODO(Category)
     * upvotes
     * downvotes
     * updated_at (most recent)
     * wish_name
     * rastagems_donated
     * rastagems_required
     */
    Column {
        TopAppBar(
            title = {
                Text(
                    text = BottomBarScreens.Home.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        )

        Categories(viewModel = viewModel)

        WishGraph(
            navController = navController,
            wishList = viewModel.allWishes,
            displayType = DisplayType.ReadOnly
        )
    }
}

@Composable
private fun Categories(viewModel: HomeViewModel) {
    val categories = listOf(
        Text(text = "Hello"),
        Text(text = "Hello"),
        Text(text = "Hello"),
        Text(text = "Hello")
    )

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(categories) { index, _ ->
            Box {
                categories[index]
            }
        }
    }
}
