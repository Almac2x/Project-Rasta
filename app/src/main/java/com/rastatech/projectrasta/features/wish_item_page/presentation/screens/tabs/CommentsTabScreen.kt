package com.rastatech.projectrasta.features.wish_item_page.presentation.screens.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.data.remote.dto.CommentsDTO
import com.rastatech.projectrasta.ui.components.CustomDonatorsItem
import com.rastatech.projectrasta.ui.components.CustomTextField
import com.rastatech.projectrasta.ui.components.GetImage
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

@Composable
fun CommentsTabScreen(
    comments: List<CommentsDTO>,
    onComment : (String) -> Unit
) {

    val comment = remember{ mutableStateOf("") }

    Scaffold( modifier = Modifier.fillMaxSize(),

        bottomBar = {

            Row(modifier = Modifier.background(Color.White),
                verticalAlignment = Alignment.CenterVertically

            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(9F),
                    value = comment.value,
                    label = {Text(text = "Comment")},

                    onValueChange = {comment.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                )

                IconButton(modifier = Modifier.weight(1F),onClick = {onComment.invoke(comment.value)}) {
                    Icon(modifier = Modifier.fillMaxSize(),
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "Comment Button"
                    )
                }

            }

        }
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = comments){ comment ->
                Box(modifier = Modifier.padding(bottom = 10.dp)) {
                    CommentCard(
                        comment = comment.comment,
                        userName = comment.user.username,
                        imageURL = comment.user.avatar ?: "",
                    )
                }

            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }

}


@Composable
private fun CommentCard(
    modifier: Modifier = Modifier,
    userName: String,
    comment: String,
    imageURL: String
) {
    Card(
        modifier = modifier.heightIn(min = 75.dp),
        elevation = 5.dp,
        shape = CardCornerRadius.small
    ) {
            Row(modifier = Modifier.padding(5.dp)) {

                Box(modifier = Modifier
                    .padding(end = 15.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(BorderStroke(1.dp, Color.LightGray), shape = CircleShape),
                    contentAlignment = Alignment.Center

                ){
                    GetImage(defaultImage = R.drawable.default_profile, imageURL = imageURL)
                }

                Column() {

                    Text(
                        text = userName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = comment,
                            fontSize = 15.sp,

                            )
                    }

                }


            }

    }
}

@Preview(showBackground = false)
@Composable
private fun Preview() {
    CommentCard(userName = "UserName", comment = " Dis is a comment" , imageURL = "https://www.w3schools.com/howto/img_avatar2.png")
}