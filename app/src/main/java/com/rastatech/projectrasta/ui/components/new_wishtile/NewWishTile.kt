package com.rastatech.projectrasta.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WisherDTO
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.ui.components.new_wishtile.NewWishTileViewModel
import com.rastatech.projectrasta.ui.components.vote_button.CustomVoteButton
import com.rastatech.projectrasta.ui.components.wish_list_page.WishPageEvents
import com.rastatech.projectrasta.ui.theme.CardCornerRadius
import com.rastatech.projectrasta.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NewWishTile (
    bottomBarNavController: NavController,
    wish: WishDTO,
    viewModel: NewWishTileViewModel = hiltViewModel()

) {

    val heartButtonSize = 35.dp
    val horizontalPadding = 10.dp

    val heart = remember { mutableStateOf(wish.liked)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        shape = CardCornerRadius.small,
        elevation = 10.dp
    ) {
        Column {  //<- Start of Card Row

            // Start -> WishName, UserName, ProfilePic
            Row(modifier = Modifier.padding(top = 10.dp,
                start = horizontalPadding, end = horizontalPadding, bottom = 5.dp)) {

                Box(modifier = Modifier
                    .padding(end = 15.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(BorderStroke(1.dp, Color.LightGray), shape = CircleShape),
                    contentAlignment = Alignment.Center

                ){
                    GetImage(defaultImage = R.drawable.default_profile,
                        imageURL = wish.user.avatar)
                }

                //WishName, Username
                WishNameRow(wishName = wish.wish_name, userName = wish.user.username)

            }// End -> WishName, UserName, ProfilePic

            //Start -> Description
            Text(modifier = Modifier.padding(horizontal = horizontalPadding),
                text = wish.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )//End -> Description

            Divider(modifier = Modifier.padding(vertical = 5.dp),
                color = Color.Gray, thickness = 0.5.dp)

            // Start -> Wish Image
            Box(modifier = Modifier
                .heightIn(max = 200.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
                ){

                GetImage(defaultImage = R.drawable.gift,
                    imageURL = wish.image_url)

            }//End -> End Wish Image

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            ){
                CustomGemProgressBar(
                    progress = wish?.rastagems_donated?:0,
                    maxProgress = wish?.rastagems_required?:0,
                    progressColor = Color.Green,
                    backgroundColor = Color.LightGray,
                    height = 25.dp
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            // Created At
            Text(modifier = Modifier.padding(start = horizontalPadding),
                text = "Created At: "+wish.created_at,
                fontSize = 14.sp,
                color = Color.Gray
            )


            Divider(modifier = Modifier.padding(top = 2.dp,bottom = 5.dp),
                color = Color.Gray, thickness = 0.5.dp)

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = horizontalPadding),

                verticalAlignment = Alignment.CenterVertically
            ){

                // Heart Button
                Box(modifier = Modifier
                    .weight(1f),
                    contentAlignment = Alignment.Center
                    ){

                    Row(modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier.size(heartButtonSize),
                            onClick = {
                                heart.value = !heart.value
                                viewModel.onEvent(WishPageEvents.LikeAWish(wishID = wish?.wish_id))
                            }
                        ) {
                            Icon(
                                tint = if (heart.value) Color.Red else Color.LightGray,
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.heart_grey),
                                contentDescription = "heart"
                            )
                        }

                        Text(modifier = Modifier.padding(start = 10.dp),
                            text = "Like",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                    }

                }

                //VoteButton
                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ){

                    CustomVoteButton(
                        upVote = remember{ mutableStateOf(wish?.upvotes?:0)},
                        downVote = remember { mutableStateOf( wish?.downvotes?:0)},
                        vote = remember { mutableStateOf(VoteType.toConvert().convert(wish?.vote_status.toString())?: VoteType.NONE) },
                        wishID = wish?.wish_id?:0
                    )

                }


            }



        }//<- End of Card Row

    }
}

@Composable
fun GetImage(
    imageURL: String? = null,
    defaultImage : Int,

){

    //Wish Image
    GlideImage(imageModel = imageURL,
        modifier = Modifier.fillMaxSize(),
        loading = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        },
        failure = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(
                        id = defaultImage
                    ),
                    contentDescription = "",
                )
            }
        }
    )

}

@Composable
fun WishNameRow(
    wishName : String,
    userName : String
){

    Row(modifier = Modifier.fillMaxWidth()) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = wishName,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = userName,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun NewWishTilePreview() {

    NewWishTile(
        bottomBarNavController = rememberNavController(),
        wish = WishDTO(
            wish_id = 1,
            wish_name = "Wish Name",
            image_url = "Url",
            description = "Descripton Description",
            rastagems_required = 21,
            rastagems_donated = 32,
            upvotes = 21,
            downvotes = 32,
            liked = true,
            vote_status = "UPVOTE",
            created_at = "2021",
            updated_at = "2021",
            user = WisherDTO(
                avatar = "Nnai",
                full_name = "Alejandro Blando",
                user_id = 1,
                username = "nani"
            )
        )
    )
}