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
    
    LazyVerticalGrid(cells = GridCells.Fixed(2),

        contentPadding = PaddingValues( start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        content = {

        items(items = wishes){ wish ->
            WishTile(wish = wish)
        }
    } )


}


@ExperimentalMaterialApi
@Composable
fun WishTile(wish : Wish? = null ){
    val heightWeight = 1f

    val imageSize = 150.dp
    val itemName = "Wish Name"
    val nameOfWisher = "Alejandro G. Blando III"

   Card(modifier = Modifier
       .height(250.dp). padding(10.dp)
       .testTag("WishtTile"), elevation = 2.dp, backgroundColor = MaterialTheme.colors.primary,
       onClick = {Log.i("Clicked","Clicked o")},
   ) {
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

           Image(painterResource(id = wish?.image ?: R.drawable.gift), contentDescription = "Image of ${wish?.wish_name}",// image of Wish
               contentScale = ContentScale.Crop
               ,modifier = Modifier
                   .width(imageSize)
                   .height(imageSize)
                   .padding(bottom = 20.dp))

           Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
               Text(text = itemName, textAlign = TextAlign.Start, style = TextStyle(

               ))
               Text(text = nameOfWisher)
           }

           // Add here Fill Bar

           // Add here Row for Heart and UpVote and Down Vote for Wish TIle
       }


   }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
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