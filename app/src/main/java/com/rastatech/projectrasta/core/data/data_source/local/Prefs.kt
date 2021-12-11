package com.rastatech.projectrasta.core.data.data_source.local

import android.content.Context
import android.content.SharedPreferences


private const val ACCESS_TOKEN = "access_token"
class Prefs (context: Context) {

    private val prefName = "SecretRastaPref"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = preferences.edit()


    var accessToken: String?
        get() = preferences.getString(ACCESS_TOKEN, null)
        set(value) = preferences.edit().putString(ACCESS_TOKEN, value).apply()

}