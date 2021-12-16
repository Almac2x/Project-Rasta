package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.about

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.theme.AppColorPalette
import com.rastatech.projectrasta.ui.theme.CardCornerRadius

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/15/2021
 */

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AboutScreen() {
    val scrollState = rememberScrollState()

        // Secret Rasta Text
        /*Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "Logo",
            modifier = Modifier.scale(0.5f)
        )*/


        val membersList = listOf<Map<String,String>>(
            mapOf("name" to "Alejandro Blando",
                "track" to "Android",
                "description" to "Replica of the great Rastaman himself. " +
                        "The Android dev and supreme leader of the team, with skills much " +
                        "like Rastaman himself as he leads the Philippines as president.",
                "qoutes" to "Push Na yan sa Main, Warning lang naman",
                "imageID" to R.drawable.me.toString()
            ),
            mapOf("name" to "Christian Lloyd Salon",
                "track" to "Android",
                "description" to "Codename Half-Human Half-Zombie of Android. " +
                        "Just like Rastaman, he has the skills of both halves: the great " +
                        "coding skills of a human and the nonstop coding capability of a zombie.",
                "qoutes" to "Anong branch branch, sa main agad",
                "imageID" to R.drawable.boss.toString()
            ),
            mapOf("name" to "Christian Kin Lascuña",
                "track" to "iOS",
                "description" to "The one man team IOS developer in Rasta Tech. He can get " +
                        "things done with great perfection even if alone due to the " +
                        "blessing of Rastamans's Dark Angel.",
                "qoutes" to "There's no I in team, only in Kin",
                "imageID" to R.drawable.kin.toString()
            ),
            mapOf("name" to "Neil Ryan Lipa-od",
                "track" to "Backend (Java Spring)",
                "description" to "The backend lord, the java god, the spring guru. " +
                        "Not only is his skills already in senior dev level even if " +
                        "he is still a trainee, he is also really pogi, more pogi than " +
                        "the great Rastaman.",
                "qoutes" to "Anong review review?",
                "imageID" to R.drawable.cto.toString()
            ),
            mapOf("name" to "Philip John Calape",
                "track" to "Backend (Java Spring)",
                "description" to "Direct shadow apostle of Nil Pogi, the backend lord. " +
                        "He acquires a great buff in coding in java spring in the presence " +
                        "of his master, much as he is enlightened by Rastaman himself.",
                "qoutes" to "While there's a code, there are bugs, Kaya madami akong Baygon sa bahay",
                "imageID" to R.drawable.calape.toString()
            ),


        )



        val aboutTile = mapOf<String,String>(
            "Our Heritage" to
                    "Our story begins in 2021 along the deep corners of Google Meet. " +
                    "It was here where we found our intense admiration for the great Rastaman, " +
                    "to whom our greatest works are dedicated. Guided by his awesome ways of making " +
                    "us happy, we want to also share the happiness to you, and that's why the company " +
                    "was born.",
            "Company Description" to
                    "Everything we do is about you. From apps to help you achieve your dreams to " +
                    "the apps that put happiness to your life - we prioritize what you need in order " +
                    "for you to have the enlightenment that Rastaman wants all of us to have. " +
                    "We strive to keep you at your best, and we remain being the best for you. " +
                    "That's what the great Rastaman wants for all of us.",
            "Company Tagline" to
                    "Wachu gonna give?"


        )

        val name = aboutTile.keys.toList()


        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            item {
                Surface(modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(175.dp), color = Color.White) {
                    // Rasta Tech Image
                    Image(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        painter = painterResource(id = R.drawable.rasta_tech),
                        contentDescription = "Company", contentScale = ContentScale.Crop
                    )
                }

                // About
                Text(
                    text = "About Us",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary
                )
            }
            items(items = name) { name ->

                CardContent(name = name, aboutTile?.get(name)?:"")

            }

            item{

                Text(
                    text = "Meet the Team",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary
                )
            }
            
            items(items = membersList){ member ->
                MemberCard(member = member)
                
            }

            item{
                Box(modifier = Modifier.height(15.dp))
            }


        }

}

@Composable
private fun MemberCard(member : Map<String,String>) {
    var expanded by remember { mutableStateOf(false) }
    
    val imageID = member["imageID"]
    val name = member["name"]?:""
    val track = member["track"]?:""
    val description = member["description"]?:""
    val qoutes = member["qoutes"]?:""
    

    Row(
        modifier = Modifier
            .padding(1.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageID?.toInt()?:R.drawable.calape),
                    contentDescription = name,
                    modifier = Modifier
                        .size(50.dp)

                )

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(
                        text = name,
                        modifier = Modifier.width(200.dp),
                        maxLines = 1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = track,
                        modifier = Modifier.width(200.dp),
                        maxLines = 1,
                        color = MaterialTheme.colors.onSurface,
                        overflow = TextOverflow.Ellipsis
                    )
                }


            }
            if (expanded) {

                Text(
                    text = (description),
                    textAlign = TextAlign.Justify
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Motivational Quote",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = qoutes,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "Show More"
                } else {
                    "Show Less"
                }

            )
        }
    }
}





@Composable
private fun CardContent(name: String, content : String,) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(5.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center

                )
            )
            if (expanded) {
                Text(
                    text = (content),
                    textAlign = TextAlign.Justify
                )
                
                
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                   "Show More"
                } else {
                   "Show Less"
                }

            )
        }
    }
}




@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun MemberItem(
    name: String,
    track: String,
    description: String,
    quotes: String,
    imageID: Int,
){
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 200,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        },
        shape = CardCornerRadius.small,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = name,
                    modifier = Modifier
                        .size(50.dp)

                )

                Spacer(modifier = Modifier.width(20.dp))
                
                Column {
                    Text(
                        text = name,
                        modifier = Modifier.width(200.dp),
                        maxLines = 1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = track,
                        modifier = Modifier.width(200.dp),
                        maxLines = 1,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Column {
                    Text(
                        text = "Description",
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Quote",
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = quotes,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    AboutScreen()
}