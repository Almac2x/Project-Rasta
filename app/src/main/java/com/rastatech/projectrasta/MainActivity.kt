package com.rastatech.projectrasta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.login.LoginScreen
import com.rastatech.projectrasta.features.userprofile.UserProfileScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.WishItemPageScreen
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.WishItemPageTabScreen
import com.rastatech.projectrasta.nav_graph.NavGraph
import com.rastatech.projectrasta.screens.HomeScreen
import com.rastatech.projectrasta.ui.components.*
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectRastaTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectRastaTheme {
        HomeScreen()
    }
}