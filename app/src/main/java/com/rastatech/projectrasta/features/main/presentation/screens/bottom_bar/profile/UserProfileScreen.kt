package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import android.app.Dialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.features.main.domain.util.DisplayType
import com.rastatech.projectrasta.features.main.domain.util.UserType
import com.rastatech.projectrasta.nav_graph.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.nav_graph.screens.BottomBarScreens
import com.rastatech.projectrasta.ui.components.CustomProfileImage
import com.rastatech.projectrasta.ui.components.CustomTextWithCount
import com.rastatech.projectrasta.ui.components.NewWishTile
import com.rastatech.projectrasta.ui.components.wish_list_page.ShimmerTile
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.skydoves.landscapist.glide.GlideImage

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

private const val TAG = "UserProfileScreen"
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun UserProfileScreen(
    bottomBarNavController: NavController,
    mainNavController: NavController? = null,
    userType: UserType,
    viewModel: UserProfileViewModel = hiltViewModel()
) {


    if (viewModel.logoutAlertDialog.value) {
        AlertDialog(
            title = {
                Text(
                    text = "Log out",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.body1
                )
            },
            text = {
                Text(text = "Are you sure you want to logout?")
            },
            onDismissRequest = { viewModel.logoutAlertDialog.value = false },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.logoutAlertDialog.value = false
                        viewModel.logout.value = true

                            prefs?.accessToken = null
                            prefs?.rememberMe = false
                            prefs?.fingerprint = false

                        mainNavController?.navigate(AUTH_GRAPH_ROUTE){

                            mainNavController.popBackStack()
                            bottomBarNavController.popBackStack()
                        }
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.logoutAlertDialog.value = false
                        viewModel.logout.value = false
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }



    

    //Hard Coded Please adapt this to Make A Wish Screen
    Scaffold(
        topBar = {
            UserTopBar(viewModel = viewModel, userType = userType, bottomBarNavController = bottomBarNavController)
        }
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 5.dp, bottom = 50.dp),

            content = {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.padding(10.dp)) {
                            CustomProfileImage(
                                url = viewModel.imageURL,
                                diameter = 150.dp,
                                borderThickness = 5.dp
                            )
                        }

                        Box(modifier = Modifier.padding(10.dp)) {
                            Column {
                                Text(
                                    text = "${viewModel.firstName} ${viewModel.lastName}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )

                                Text(
                                    text = viewModel.userName,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    fontSize = 15.sp
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CustomTextWithCount(title = "Active Wishes", count = viewModel.numberOfActiveWishes)
                            CustomTextWithCount(title = "Wishes Fulfilled", count = viewModel.numberOfFulfiledWishes)
                        }

                        Box(modifier = Modifier
                            .fillMaxWidth()
                        ) {
                            UserProfileTabScreen(
                                userType = userType,
                                viewModel = viewModel,
                                bottomNavController = bottomBarNavController
                            )
                        }
                    }
                }

            }
        )




    }
}

@Composable
private fun UserTopBar(viewModel: UserProfileViewModel, userType: UserType, bottomBarNavController: NavController) {
    TopAppBar(
        backgroundColor = AppColorPalette.background,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            // Profile Text
            Text(
                text = "Profile",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            if (userType == UserType.Current) {
                // Logout
                IconButton(
                    onClick = {
                        viewModel.logoutAlertDialog.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Logout"
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart,

            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    if (userType == UserType.Other) {
                        // Back Button
                        IconButton(
                            onClick = {
                                bottomBarNavController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Text(
                            text = "Back",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }else{
                        // Settings
                        IconButton(
                            onClick = {
                                viewModel.settingsAlertDialog.value = true

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings"
                            )

                        }

                        if(viewModel.settingsAlertDialog.value){
                            SettingDialog(viewModel.settingsAlertDialog)
                        }

                    }
                }
            }
        }
    }
}


@Composable
private fun SettingDialog(isVisible : MutableState<Boolean>){


    val fingerprint = remember { mutableStateOf(prefs?.fingerprint)}


    AlertDialog(
        onDismissRequest = { isVisible.value = false },

        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.size(width = 200.dp, height = 200.dp)
            ) {

                Text(text = "Enable Fingerprint: ", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            }

        },
        confirmButton = {

            Switch(modifier = Modifier.padding(start = 10.dp),
                checked = fingerprint.value ?: false,
                onCheckedChange = {
                    fingerprint.value = it
                    prefs?.fingerprint = it
                    Log.i( TAG, "${prefs?.fingerprint}")
                }
            )
        },
    )


}



@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    UserProfileScreen(bottomBarNavController = rememberNavController(), userType = UserType.Current, mainNavController = rememberNavController(

    ))
}