package com.rastatech.projectrasta

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rastatech.projectrasta.models.wish.Wish
import com.rastatech.projectrasta.ui.theme.ProjectRastaTheme

class MainActivity : ComponentActivity() {
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

@Composable
fun HomeScreen(){

    var wishes: List<Wish> = listOf(Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift),Wish(wish_id = "id", wish_name = "Nani", description = "Description", rastagems_donated = 15, rastagems_required = 15,
        user_id = "121", image = R.drawable.gift))
    wishes = wishes+wishes

    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(items = wishes){ i, wish ->

            Row() {
                WishTile(wish = wish)
                if(i != wishes.size-1)
                    WishTile(wish = wishes[i+1])
            }


        }


    }


}


@Composable
fun WishTile(wish : Wish? = null ){
    val height = 350.dp
    val width = 250.dp
    val imageSize = 150.dp
    val itemName = "Wish Name"
    val nameOfWisher = "Alejandro G. Blando III"

   Card(modifier = Modifier
       .width(width)
       .height(height)
       .testTag("WishtTile"), elevation = 2.dp) {
       Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

           Image(painterResource(id = wish?.image ?: R.drawable.gift), contentDescription = "Image of ${wish?.wish_name}",// image of Wish
               modifier = Modifier
                   .width(imageSize)
                   .height(imageSize)
                   .padding(bottom = 20.dp))
           Text(text = itemName)
           Text(text = nameOfWisher)

           // Add here Fill Bar

           // Add here Row for Heart and UpVote and Downvote for Wisht TIle
       }


   }

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectRastaTheme {
        WishTile()
    }
}