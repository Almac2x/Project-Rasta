package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.ui.components.CustomSortItem
import com.rastatech.projectrasta.ui.components.NewWishTile
import com.rastatech.projectrasta.ui.components.wish_list_page.ShimmerTile
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.widget.ConstraintLayout
import com.rastatech.projectrasta.features.main.domain.util.DisplayType


@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    bottomBarNavController: NavController,
    viewModel:HomeViewModel = hiltViewModel(),
){
    /**
     * TODO(Category)
     * upvotes
     * downvotes
     * updated_at (most recent)
     * wish_name
     * rastagems_donated
     * rastagems_required
     */

    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }


    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = 0.dp,
            color = Color.White
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(.9f)
                    ,
                    value = viewModel.query.value,
                    onValueChange = { viewModel.query.value = it },
                    label = { },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                                 viewModel.onEvent(HomeEvents.Search(query = viewModel.query.value.text))
                        },
                    ),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),

                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                )

                Box(contentAlignment = Alignment.Center,modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        rotationZ = 90f
                    }){


                    IconButton(onClick = {
                        expanded = true

                    }) {
                        Icon(Icons.Filled.CompareArrows, contentDescription = "Search Icon")
                    }

                  DropdownMenu(modifier =Modifier.background(Color.White),
                      expanded = expanded, onDismissRequest = { expanded = !expanded }) {

                      DropdownMenuItem(onClick = { expanded = !expanded

                                                viewModel.direction.value = "asc"
                            }) {
                          Text(text = "Ascending")

                      }
                      DropdownMenuItem(onClick = { expanded = !expanded
                                                    viewModel.direction.value = "desc"
                            }) {
                          Text(text = "Descending")

                      }

                  }

                }

            }

        }


        CategoriesDropDown("Categories","2132", viewModel = viewModel)

       // Categories(viewModel = viewModel, 30.dp)


        LazyColumn(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 5.dp, bottom = 50.dp),

            content = {

                if(viewModel.allWishes.isEmpty()){

                    items(count = 5){
                        ShimmerTile()
                    }
                }else {
                    items(items = viewModel.allWishes,){ wish ->
                        NewWishTile(bottomBarNavController = bottomBarNavController, wish = wish, displayType = DisplayType.ReadOnly)
                    }
                }
            }
        )


    }
}


@ExperimentalFoundationApi
@Composable
private fun CategoriesDropDown(name: String, content : String,viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(5.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center

                )
            )

            if (expanded) {
                Surface(modifier = Modifier.fillMaxWidth()){
                    Categories(viewModel = viewModel)
                }
            }

        }


        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "Show More"
                } else {
                    "Show Less"
                }
            )

        }
    }
}




@ExperimentalFoundationApi
@Composable
private fun Categories(viewModel: HomeViewModel,) {
    val sortBy = listOf(
        Sort.Upvote,
        Sort.Downvote,
        Sort.Recent,
        Sort.Donated,
        Sort.Liked
    )

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        sortBy.forEach {
            sort ->
            CustomSortItem(
                sortItem = sort,
                onClick = {
                    when(sort) {
                        Sort.Upvote -> {viewModel.onEvent(HomeEvents.GetFilteredWishes(sort = Sort.Upvote.value, direction = viewModel.direction.value))}
                        Sort.Downvote -> {viewModel.onEvent(HomeEvents.GetFilteredWishes(sort = Sort.Downvote.value, direction = viewModel.direction.value))}
                        Sort.Recent -> {viewModel.onEvent(HomeEvents.GetFilteredWishes( sort = "updatedAt", direction = viewModel.direction.value))}
                        Sort.Donated -> {viewModel.onEvent(HomeEvents.GetFilteredWishes(sort = Sort.Donated.value, direction = viewModel.direction.value))}
                        Sort.Liked ->{viewModel.onEvent(HomeEvents.GetLikedWishes)}
                    }
                }
            )
        }
    }
}
