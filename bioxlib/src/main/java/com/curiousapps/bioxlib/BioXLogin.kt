package com.curiousapps.bioxlib

import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BioXLogin constructor(bioxBuilder: BioXBuilder) {

    companion object{
        val TAG = "Awesomness"

        private const val TAGME: String = "BioStart"
        private const val TAGBIO: String = "BioOpened"


        fun d(message: String?) {
            Log.d(TAG, message)
        }
    }

    private val context: FragmentActivity
    private val title: String
    private val description: String
    private val negativeButtonText: String
    private var biometricPrompt: BiometricPrompt? = null

    fun bioMet() {
        val executor = ContextCompat.getMainExecutor(context)
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Log.d(TAGME, "$errorCode :: $errString")
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON)
                    Toast.makeText(
                        context,
                        "User Cancelled : $errString", Toast.LENGTH_LONG
                    ).show()
                if (errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
                    Toast.makeText(
                        context,
                        "User Cancelled : $errString", Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d(TAGME, "Unrecognized Fingerprint")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d(TAGME, "<<< Login Success >>>")
            }
        }
        biometricPrompt = BiometricPrompt(context, executor, callback)
        openBio()
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setNegativeButtonText(negativeButtonText)
            .build()
    }

    private fun openBio() {
        Log.d(TAGBIO, "<++ Biometric opened ++>")
        val promptInfo = createPromptInfo()
        if (BiometricManager.BIOMETRIC_SUCCESS == BiometricManager
                .from(context)
                .canAuthenticate()
        ) {
            Log.d(TAGBIO, "No error detected. Login in progress.")
            biometricPrompt?.authenticate(promptInfo)
        } else {

        }
        if (BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE == BiometricManager
                .from(context)
                .canAuthenticate()
        ) {
            Log.d(TAGBIO, "This device does not have a biometric scanner.")
            Toast.makeText(
                context,
                "This device does not have a biometric scanner.", Toast.LENGTH_LONG
            ).show()
        }
        if (BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED == BiometricManager
                .from(context)
                .canAuthenticate()
        ) {
            Log.d(TAGBIO, "The user does not have any biometrics enrolled.")
            Toast.makeText(
                context,
                "The user does not have any biometrics enrolled.", Toast.LENGTH_LONG
            ).show()

            //TODO take user to the biometric settings: likely show a
            // dialog and give user a choice to set up or continue where they are.
        }
        if (BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE == BiometricManager
                .from(context)
                .canAuthenticate()
        ) {
            Log.d(TAGBIO, "Biometric hardware is unavailable.")
            Toast.makeText(
                context,
                "Biometric hardware is unavailable. \n" +
                        " Please wait for scanner to come online", Toast.LENGTH_LONG
            ).show()
        }

    }
    class BioXBuilder(val context: FragmentActivity) {
        var title: String
        var description: String
        var negativeButtonText: String

        init {
            title = "Default Title"
            description = "Secure Biometric Login"
            negativeButtonText = "Use Password/Cancel"
        }

        fun setTitle(title: String): BioXBuilder {
            this.title = title
            return this
        }

        fun setDescription(description: String): BioXBuilder {
            this.description = description
            return this
        }

        fun setNegativeButtonText(negativeButtonText: String): BioXBuilder {
            this.negativeButtonText = negativeButtonText
            return this
        }

        fun build(): BioXLogin {
            return BioXLogin(this)
        }
    }
    init {
        context = bioxBuilder.context
        title = bioxBuilder.title
        description = bioxBuilder.description
        negativeButtonText = bioxBuilder.negativeButtonText
    }

}