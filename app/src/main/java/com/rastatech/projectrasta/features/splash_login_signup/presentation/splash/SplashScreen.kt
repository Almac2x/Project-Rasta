package com.rastatech.projectrasta.features.splash.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rastatech.projectrasta.nav_graph.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember{
        Animatable(0f)

    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }

            )
        )

        delay(3000L)
        navController.navigate(AUTH_GRAPH_ROUTE){

            popUpTo(route = AuthScreens.Splash.route){
                inclusive = true
            }

        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

            Image(
                painter = painterResource(id = R.drawable.logo),  // insert here the logo of the app
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value))

            Image(
                painter = painterResource(id = R.drawable.title),  // insert here the logo of the app
                contentDescription = "Title",
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 100.dp)) // Hard Coded Please Change

    }

}
