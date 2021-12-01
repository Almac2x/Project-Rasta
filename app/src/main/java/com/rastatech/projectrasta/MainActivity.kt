package com.rastatech.projectrasta

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.models.wish.Wish
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
                    HomeScreen()
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