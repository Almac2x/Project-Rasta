package com.rastatech.projectrasta

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.features.wish_item_page.presentation.screens.WishItemPageScreen
import com.rastatech.projectrasta.nav_graph.NavGraph
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(){
    private lateinit var navController: NavHostController


    @ExperimentalAnimationApi
    @RequiresApi(Build.VERSION_CODES.N)
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectRastaTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()

              val context = LocalContext.current
                NavGraph(navController = navController, context =context)
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

    }
}