package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.ui.components.CustomProfileImage
import com.rastatech.projectrasta.ui.components.CustomTextWithCount

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/07/2021
 */

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun UserProfileScreen(
    firstName: String,
    lastName: String,
    userName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Back Button
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Back",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        CustomProfileImage(
            painter = painterResource(id = R.drawable.profile),
            diameter = 150.dp,
            borderThickness = 5.dp
        )

        Box(modifier = Modifier.padding(10.dp)) {
            Column {
                Text(
                    text = "$firstName $lastName",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = userName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 15.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomTextWithCount(title = "Active Wishes", count = 5)
            CustomTextWithCount(title = "Wishes Fulfilled", count = 5)
        }

        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            UserProfileTabScreen(
                wishList = listOf(),
                wishFulfilled = listOf()
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun Preview() {
    UserProfileScreen(
        firstName = "Christian Lloyd",
        lastName = "Salon",
        userName = "christian.salon"
    )
}