package com.rastatech.projectrasta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rastatech.projectrasta.models.wish.Wish
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.rastatech.projectrasta.login.LoginScreen
import com.rastatech.projectrasta.signup.SignUpScreen
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme
import com.rastatech.projectrasta.ui.theme.components.WishList

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectRastaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(){

    var wishes: List<Wish> = listOf(Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift),Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift))
    wishes = wishes+wishes
    wishes = wishes+wishes
    
    WishList(wishes = wishes)

}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectRastaTheme {
        HomeScreen()
    }
}