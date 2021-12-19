package com.rastatech.projectrasta.features.fingerprint

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.View


import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.rastatech.projectrasta.R
import java.util.concurrent.Executor


class Fingerprint(
    context : Context
) {

    val context = context as FragmentActivity


    val executor = ContextCompat.getMainExecutor(context)
    val biometricManager = BiometricManager.from(context)

    val title = context.getString(R.string.prompt_info_title)
    val subtitle = context.getString(R.string.prompt_info_title)
    val descripton = context.getString(R.string.prompt_info_title)


     fun authUser(executor: Executor, onNavigate : () -> Unit) {
        // 1
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            // 2
            .setTitle(title)
            // 3
            .setSubtitle(subtitle)
            // 4
            .setDescription(descripton)
            // 5
            .setDeviceCredentialAllowed(true)
            // 6
            .build()

        // 1
        val biometricPrompt = BiometricPrompt(context, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                // 2
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onNavigate.invoke()

                }

                // 3
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)

                }

                // 4
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                }
            })

        biometricPrompt.authenticate(promptInfo)
    }



}