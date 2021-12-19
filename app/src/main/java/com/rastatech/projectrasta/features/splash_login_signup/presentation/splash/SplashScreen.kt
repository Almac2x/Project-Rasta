package com.rastatech.projectrasta.features.splash.presentation.screens

import android.content.Context
import android.util.Log
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.rastatech.projectrasta.nav_graph.AUTH_GRAPH_ROUTE
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.nav_graph.screens.AuthScreens
import kotlinx.coroutines.delay
import com.rastatech.projectrasta.SecretRastaApp.Companion.prefs
import com.rastatech.projectrasta.features.fingerprint.Fingerprint
import com.rastatech.projectrasta.nav_graph.HOME_GRAPH_ROUTE




private const val TAG = "SplashScreen"
@Composable
fun SplashScreen(navController: NavController, context: Context) {
    val scale = remember{
        Animatable(0f)
    }

    val context = LocalContext.current as FragmentActivity

    val fingerprint = Fingerprint(context = context)


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

        Log.i(TAG, "access_token: ${prefs?.accessToken} \n ${prefs?.rememberMe}" )


            if(prefs?.rememberMe == true && prefs?.rememberMe != null){


                if(prefs?.fingerprint == true){
                    when (fingerprint.biometricManager.canAuthenticate()) {
                        BiometricManager.BIOMETRIC_SUCCESS ->{

                            val navigate = {navController.navigate(HOME_GRAPH_ROUTE + "/token"){

                                popUpTo(route = AuthScreens.Splash.route){
                                    inclusive = true
                                }
                            }}

                            fingerprint.authUser(fingerprint.executor, onNavigate =  navigate)
                        }

                        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->{

                            navController.navigate(HOME_GRAPH_ROUTE + "/token"){

                                popUpTo(route = AuthScreens.Splash.route){
                                    inclusive = true
                                }
                            }

                        }

                        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->{

                            navController.navigate(HOME_GRAPH_ROUTE + "/token"){

                                popUpTo(route = AuthScreens.Splash.route){
                                    inclusive = true
                                }
                            }
                        }

                        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->{
                            navController.navigate(HOME_GRAPH_ROUTE + "/token"){

                                popUpTo(route = AuthScreens.Splash.route){
                                    inclusive = true
                                }
                            }
                        }

                    }

                }else{

                    navController.navigate(HOME_GRAPH_ROUTE + "/token"){

                        popUpTo(route = AuthScreens.Splash.route){
                            inclusive = true
                        }
                    }

                }

            }else {
                navController.navigate(AUTH_GRAPH_ROUTE){

                    popUpTo(route = AuthScreens.Splash.route){
                        inclusive = true
                    }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 100.dp)) // Hard Coded Please Change

    }



}
