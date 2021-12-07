package com.rastatech.projectrasta.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.rastatech.projectrasta.ui.theme.AppColorPalette

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/06/2021
 */

enum class VoteState {
    NONE,
    UPVOTE,
    DOWNVOTE
}

@Composable
fun CustomVoteButton(
    upvoteCount: Int,
    downVoteCount: Int,
    voteState: VoteState
) {
    val upVote = remember { mutableStateOf(upvoteCount) }
    val downVote = remember { mutableStateOf(downVoteCount) }
    val vote = remember { mutableStateOf(voteState) }

    Row {
        // UpVote
        IconButton(
            onClick = {
                when (vote.value) {
                    VoteState.NONE -> {
                        vote.value = VoteState.UPVOTE
                        upVote.value = upVote.value + 1
                    }
                    VoteState.UPVOTE -> {
                        // remove count from upvote if you click upvote button again
                        vote.value = VoteState.NONE
                        upVote.value = upVote.value - 1
                    }
                    VoteState.DOWNVOTE -> {
                        vote.value = VoteState.UPVOTE
                        upVote.value = upVote.value + 1
                        downVote.value = downVote.value - 1
                    }
                }
            }
        ) {
            Icon(
                tint = if (vote.value == VoteState.UPVOTE) Color.Blue else Color.Black,
                modifier = Modifier.fillMaxSize(fraction = 0.8f),
                painter = painterResource(
                    id = R.drawable.upvote
                ),
                contentDescription = ""
            )
        }
        Text(
            color = if (vote.value == VoteState.UPVOTE) Color.Blue else Color.Black,
            fontSize = 20.sp,
            text = "${upVote.value}",
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        // DownVote
        IconButton(
            onClick = {
                when (vote.value) {
                    VoteState.NONE -> {
                        vote.value = VoteState.DOWNVOTE
                        downVote.value = downVote.value + 1
                    }
                    VoteState.DOWNVOTE -> {
                        // remove count from upvote if you click upvote button again
                        vote.value = VoteState.NONE
                        downVote.value = downVote.value - 1
                    }
                    VoteState.UPVOTE -> {
                        vote.value = VoteState.DOWNVOTE
                        downVote.value = downVote.value + 1
                        upVote.value = upVote.value - 1
                    }
                }
            }
        ) {
            Icon(
                tint = if (vote.value == VoteState.DOWNVOTE) Color.Blue else Color.Black,
                modifier = Modifier.fillMaxSize(fraction = 0.8f),
                painter = painterResource(
                    id = R.drawable.downvote
                ),
                contentDescription = ""
            )
        }
        Text(
            color = if (vote.value == VoteState.DOWNVOTE) Color.Blue else Color.Black,
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
        voteState = VoteState.NONE
    )
}