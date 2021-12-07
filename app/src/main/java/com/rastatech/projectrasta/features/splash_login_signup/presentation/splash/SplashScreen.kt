package com.rastatech.projectrasta.features.splash.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
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


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),  // inser here the logo of the app
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }

}
