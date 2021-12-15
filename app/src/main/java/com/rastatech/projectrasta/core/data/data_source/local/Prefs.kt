package com.rastatech.projectrasta.core.data.data_source.local

import android.content.Context
import android.content.SharedPreferences


private const val ACCESS_TOKEN = "access_token"
private const val REMEMBER_ME = "remember_me"
class Prefs (context: Context) {

    private val prefName = "SecretRastaPref"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = preferences.edit()



    var rememberMe : Boolean?
        get() = preferences.getBoolean(REMEMBER_ME, null == false)
        set(value) = preferences.edit().putBoolean(REMEMBER_ME,value ?: false).apply()

    var accessToken: String?
        get() = preferences.getString(ACCESS_TOKEN, null)
        set(value) = preferences.edit().putString(ACCESS_TOKEN, value).apply()

}