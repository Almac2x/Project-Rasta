package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import com.rastatech.projectrasta.features.main.domain.util.ShowType

sealed class GemPageEvents{


    data class AddGems(val showType: ShowType,val amount : Int,): GemPageEvents()
    data class SendGems(val showType: ShowType, val amount : Int, val userName : String): GemPageEvents()

    data class AddGemDialog(val showType: ShowType): GemPageEvents()
    data class SendGemDialog(val showType: ShowType): GemPageEvents()
    data class TransactionGemDialog(val showType: ShowType): GemPageEvents()

    data class CheckInput (val input: String) : GemPageEvents()


}
