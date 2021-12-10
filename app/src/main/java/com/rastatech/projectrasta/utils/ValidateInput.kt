package com.rastatech.projectrasta.utils

import android.util.Log
import androidx.core.text.isDigitsOnly

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/09/2021
 */
class ValidateInput {
    companion object {
        fun isNumber(input: String): Boolean {
            Log.i(input, input)
            return input.isDigitsOnly()
        }
    }
}