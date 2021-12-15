package com.rastatech.projectrasta.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rastatech.projectrasta.R
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.utils.Convert

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomVoteButton(
    upvoteCount: Int,
    downVoteCount: Int,
    voteType: VoteType
) {
    val upVote = remember { mutableStateOf(upvoteCount) }
    val downVote = remember { mutableStateOf(downVoteCount) }
    val vote = remember { mutableStateOf(voteType) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // UPVOTE
        Box {
            Row {
                IconButton(
                    onClick = {
                        when (vote.value) {
                            VoteType.NONE -> {
                                vote.value = VoteType.UPVOTE
                                upVote.value = upVote.value + 1
                            }
                            VoteType.UPVOTE -> {
                                // remove count from upvote if you click upvote button again
                                vote.value = VoteType.NONE
                                upVote.value = upVote.value - 1
                            }
                            VoteType.DOWNVOTE -> {
                                vote.value = VoteType.DOWNVOTE
                                upVote.value = upVote.value + 1
                                downVote.value = downVote.value - 1
                            }
                        }
                    }
                ) {
                    Icon(
                        tint = if (vote.value == VoteType.UPVOTE) Color.Blue else Color.Black,
                        modifier = Modifier.fillMaxSize(fraction = 0.8f),
                        imageVector = if (vote.value == VoteType.UPVOTE)
                            Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                        contentDescription = ""
                    )
                }
                Text(
                    color = if (vote.value == VoteType.UPVOTE) Color.Blue else Color.Black,
                    fontSize = 20.sp,
                    text = Convert.toCompactNumber(upVote.value),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        // DOWNVOTE
        Box {
            Row {
                IconButton(
                    onClick = {
                        when (vote.value) {
                            VoteType.NONE -> {
                                vote.value = VoteType.DOWNVOTE
                                downVote.value = downVote.value + 1
                            }
                            VoteType.DOWNVOTE -> {
                                // remove count from upvote if you click upvote button again
                                vote.value = VoteType.NONE
                                downVote.value = downVote.value - 1
                            }
                            VoteType.UPVOTE -> {
                                vote.value = VoteType.DOWNVOTE
                                downVote.value = downVote.value + 1
                                upVote.value = upVote.value - 1
                            }
                        }
                    }
                ) {
                    Icon(
                        tint = if (vote.value == VoteType.DOWNVOTE) Color.Blue else Color.Black,
                        modifier = Modifier.fillMaxSize(fraction = 0.8f),
                        imageVector = if (vote.value == VoteType.DOWNVOTE)
                            Icons.Filled.ThumbDown else Icons.Outlined.ThumbDown,
                        contentDescription = ""
                    )
                }
                Text(
                    color = if (vote.value == VoteType.DOWNVOTE) Color.Blue else Color.Black,
                    fontSize = 20.sp,
                    text = Convert.toCompactNumber(downVote.value),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    CustomVoteButton(
        upvoteCount = 2000,
        downVoteCount = 1600,
        voteType = VoteType.NONE
    )
}