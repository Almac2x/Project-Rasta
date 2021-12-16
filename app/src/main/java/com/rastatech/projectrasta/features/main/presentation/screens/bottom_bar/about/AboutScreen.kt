package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.about

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Secret Rasta Text
        /*Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "Logo",
            modifier = Modifier.scale(0.5f)
        )*/

        Surface(modifier = Modifier.fillMaxWidth().height(175.dp), color = Color.White) {
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
            text = "About",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(50.dp)
                    .background(color = Color.Red, shape = CardCornerRadius.small)
            )
        }

        Text(
            text = "Our Heritage",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Our story begins in 2021 along the deep corners of Google Meet. " +
                    "It was here where we found our intense admiration for the great Rastaman, " +
                    "to whom our greatest works are dedicated. Guided by his awesome ways of making " +
                    "us happy, we want to also share the happiness to you, and that's why the company " +
                    "was born.",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Company Description",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Everything we do is about you. From apps to help you achieve your dreams to " +
                    "the apps that put happiness to your life - we prioritize what you need in order " +
                    "for you to have the enlightenment that Rastaman wants all of us to have. " +
                    "We strive to keep you at your best, and we remain being the best for you. " +
                    "That's what the great Rastaman wants for all of us.",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Tagline",
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Wachu gonna give?",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // Meet the Team (drop down items)
        Card(
            shape = CardCornerRadius.small,
            backgroundColor = Color(0xFF0A2D49),
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "Meet the Team",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(50.dp)
                            .background(color = Color.Red, shape = CardCornerRadius.small)
                    )
                }

                Column(modifier = Modifier.padding(top = 10.dp)) {
                    MemberItem(
                        name = "Alejandro Blando",
                        track = "Android",
                        description = "Replica of the great Rastaman himself. " +
                                "The Android dev and supreme leader of the team, with skills much " +
                                "like Rastaman himself as he leads the Philippines as president.",
                        quotes = "Push Na yan sa Main, Warning lang naman",
                        imageID = R.drawable.me
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    MemberItem(
                        name = "Christian Lloyd Salon",
                        track = "Android",
                        description = "Codename Half-Human Half-Zombie of Android. " +
                                "Just like Rastaman, he has the skills of both halves: the great " +
                                "coding skills of a human and the nonstop coding capability of a zombie.",
                        quotes = "Anong branch branch, sa main agad",
                        imageID = R.drawable.boss
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    MemberItem(
                        name = "Christian Kin Lascuña",
                        track = "iOS",
                        description = "The one man team IOS developer in Rasta Tech. He can get " +
                                "things done with great perfection even if alone due to the " +
                                "blessing of Rastamans's Dark Angel.",
                        quotes = "There's no I in team, only in Kin",
                        imageID = R.drawable.kin
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    MemberItem(
                        name = "Neil Ryan Lipa-od",
                        track = "Backend",
                        description = "The backend lord, the java god, the spring guru. " +
                                "Not only is his skills already in senior dev level even if " +
                                "he is still a trainee, he is also really pogi, more pogi than " +
                                "the great Rastaman.",
                        quotes = "Anong review review?",
                        imageID = R.drawable.cto
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    MemberItem(
                        name = "Philip John Calape",
                        track = "Backend",
                        description = "Direct shadow apostle of Nil Pogi, the backend lord. " +
                                "He acquires a great buff in coding in java spring in the presence " +
                                "of his master, much as he is enlightened by Rastaman himself.",
                        quotes = "\"While there's a code, there are bugs",
                        imageID = R.drawable.calape
                    )
                }
            }
        }

        // item: (picture) (name, track) (dropdown button)
        // inside: description
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = name,
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = AppColorPalette.secondary,
                            shape = CircleShape
                        )
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