package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */

enum class VoteType {
    NONE,
    UPVOTE,
    DOWNVOTE
}

@Composable
fun CustomVoteButton(
    upvoteCount: Int,
    downVoteCount: Int,
    voteType: VoteType
) {
    val upVote = remember { mutableStateOf(upvoteCount) }
    val downVote = remember { mutableStateOf(downVoteCount) }
    val vote = remember { mutableStateOf(voteType) }

    Row {
        // UpVote
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
                        vote.value = VoteType.UPVOTE
                        upVote.value = upVote.value + 1
                        downVote.value = downVote.value - 1
                    }
                }
            }
        ) {
            Icon(
                tint = if (vote.value == VoteType.UPVOTE) Color.Blue else Color.Black,
                modifier = Modifier.fillMaxSize(fraction = 0.8f),
                painter = painterResource(
                    id = R.drawable.upvote
                ),
                contentDescription = ""
            )
        }
        Text(
            color = if (vote.value == VoteType.UPVOTE) Color.Blue else Color.Black,
            fontSize = 20.sp,
            text = "${upVote.value}",
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        // DownVote
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
                painter = painterResource(
                    id = R.drawable.downvote
                ),
                contentDescription = ""
            )
        }
        Text(
            color = if (vote.value == VoteType.DOWNVOTE) Color.Blue else Color.Black,
            fontSize = 20.sp,
            text = "${downVote.value}",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CustomVoteButton(
        upvoteCount = 200,
        downVoteCount = 100,
        voteType = VoteType.NONE
    )
}