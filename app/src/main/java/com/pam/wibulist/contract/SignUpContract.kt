package com.pam.wibulist.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.pam.wibulist.SignUpActivity

class SignUpContract : ActivityResultContract<String?, String??>(){
    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, SignUpActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when{
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra("email")
    }
}