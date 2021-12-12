package com.rastatech.projectrasta.utils

import android.icu.text.CompactDecimalFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/12/2021
 */
class Convert {
    companion object {
        @RequiresApi(Build.VERSION_CODES.N)
        fun toCompactNumber(amount: Int): String {
            return CompactDecimalFormat.getInstance(
                    Locale.getDefault(), CompactDecimalFormat.CompactStyle.SHORT
            ).format(amount)
        }
    }
}