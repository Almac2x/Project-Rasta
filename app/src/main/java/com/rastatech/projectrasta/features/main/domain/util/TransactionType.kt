package com.rastatech.projectrasta.features.main.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.Remove
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TransactionType(

    val amount : Int,
    val wishID : Int

) {
     class Donate(
         wishID: Int,
         amount: Int,
    ): TransactionType(amount = amount, wishID = wishID){
          val icon: ImageVector = Icons.Default.CardGiftcard
         companion object {
             const val value = "Donate"

         }
     }

    class Add(wishID: Int,
              amount: Int,
    ): TransactionType(amount = amount, wishID = wishID) {

        val icon: ImageVector = Icons.Default.Add

        companion object {
            const val value = "Add"

        }
    }

     class Send(
        wishID: Int,
        amount: Int,
    ): TransactionType(amount = amount, wishID = wishID){
        val icon = Icons.Default.Remove
         companion object {
             const val value = "Send"

         }
    }

}