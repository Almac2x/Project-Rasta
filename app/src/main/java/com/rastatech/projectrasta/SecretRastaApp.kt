package com.rastatech.projectrasta

import android.app.Application
import com.rastatech.projectrasta.core.data.data_source.local.Prefs
import dagger.hilt.android.HiltAndroidApp


private val prefs: Prefs by lazy {
    SecretRastaApp.prefs!!
}

@HiltAndroidApp
class SecretRastaApp: Application() {

    companion object{
        var prefs : Prefs? =null
        lateinit var instance : SecretRastaApp
                private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}