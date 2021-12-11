package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import com.rastatech.projectrasta.features.main.domain.util.ShowType

sealed class GemPageEvents{


    object AddGems : GemPageEvents()
    object SendGems: GemPageEvents()

    data class AddGemDialog(val showType: ShowType): GemPageEvents()
    data class SendGemDialog(val showType: ShowType): GemPageEvents()
    data class TransactionGemDialog(val showType: ShowType): GemPageEvents()

    data class CheckInput (val input: String) : GemPageEvents()



}
