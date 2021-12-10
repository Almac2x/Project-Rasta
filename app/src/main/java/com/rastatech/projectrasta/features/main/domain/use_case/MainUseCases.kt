package com.rastatech.projectrasta.features.main.domain.use_case

import com.rastatech.projectrasta.features.main.domain.use_case.main_use_case.GetOwnProfile
import com.rastatech.projectrasta.features.main.domain.use_case.main_use_case.GetUserBalance

data class MainUseCases(

    val getOwnProfile: GetOwnProfile ,
    val getUserBalance: GetUserBalance,
)
